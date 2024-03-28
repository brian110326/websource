package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDto;
import dto.SearchDto;

public class BoardDao {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Context initContext;
        try {
            initContext = new InitialContext();
            // java:/comp/env : 등록된 이름들을 관리하는 곳
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            con = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public List<BoardDto> getList() {
        con = getConnection();
        // String sql = "SELECT BNO ,TITLE ,NAME ,REGDATE ,READ_COUNT ,RE_LEV FROM BOARD
        // b ORDER BY RE_REF DESC, RE_SEQ";

        String sql = "SELECT bno, title, name, REGDATE ,READ_COUNT ,RE_LEV ";
        sql += "FROM(SELECT rownum AS rnum, A.* ";
        sql += "FROM (SELECT rownum, bno, title, name, REGDATE ,READ_COUNT ,RE_LEV ";
        sql += "FROM BOARD b WHERE bno > 0 ";
        sql += "ORDER BY RE_REF DESC, RE_SEQ) A ";
        sql += "WHERE rownum <= ?) WHERE rnum > ?";
        List<BoardDto> list = new ArrayList<>();

        int start = 0;
        int end = 0;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setReadCount(rs.getInt(5));
                dto.setReLev(rs.getInt(6));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    public int create(BoardDto insertDto) {
        con = getConnection();
        int result = 0;
        String sql = "INSERT INTO board(bno,name,password,title,content,attatch,re_ref,re_lev,re_seq) VALUES(board_seq.nextval,?,?,?,?,?,board_seq.currval,?,?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, insertDto.getName());
            pstmt.setString(2, insertDto.getPassword());
            pstmt.setString(3, insertDto.getTitle());
            pstmt.setString(4, insertDto.getContent());
            pstmt.setString(5, insertDto.getAttatch());
            pstmt.setInt(6, 0);
            pstmt.setInt(7, 0);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    public int update(BoardDto updateDto) {
        con = getConnection();
        // bno와 password 일치 시 제목과 내용 수정
        String sql = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE BNO=? AND PASSWORD=?";
        int result = 0;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, updateDto.getTitle());
            pstmt.setString(2, updateDto.getContent());
            pstmt.setInt(3, updateDto.getBno());
            pstmt.setString(4, updateDto.getPassword());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    public int delete(BoardDto deleteDto) {
        con = getConnection();
        String sql = "DELETE FROM BOARD WHERE BNO=? AND PASSWORD=?";
        int result = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, deleteDto.getBno());
            pstmt.setString(2, deleteDto.getPassword());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    public BoardDto getRow(int bno) {
        con = getConnection();
        BoardDto dto = null;
        String sql = "SELECT BNO,NAME,TITLE,CONTENT,ATTATCH, re_ref,re_seq,re_lev FROM BOARD WHERE BNO=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new BoardDto();
                dto.setBno(rs.getInt("bno"));
                dto.setName(rs.getString("name"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setAttatch(rs.getString("attatch"));
                // reply에서 필요
                dto.setReRef(rs.getInt("re_ref"));
                dto.setReSeq(rs.getInt("re_seq"));
                dto.setReLev(rs.getInt("re_lev"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return dto;
    }

    // 댓글 작성
    public int reply(BoardDto replyDto) {
        con = getConnection();
        int result = 0;

        try {
            // 원본글의 re_ref, re_lev,re_seq 가져오기
            int reRef = replyDto.getReRef();
            int reSeq = replyDto.getReSeq();
            int reLev = replyDto.getReLev();

            String sql = "UPDATE BOARD SET RE_SEQ = RE_SEQ + 1 WHERE RE_REF = ? AND RE_SEQ > ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reRef);
            pstmt.setInt(2, reSeq);

            pstmt.executeUpdate();

            sql = "INSERT INTO board(bno,name,password,title,content,re_ref,re_lev,re_seq) VALUES(board_seq.nextval,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, replyDto.getName());
            pstmt.setString(2, replyDto.getPassword());
            pstmt.setString(3, replyDto.getTitle());
            pstmt.setString(4, replyDto.getContent());
            pstmt.setInt(5, reRef);
            pstmt.setInt(6, reLev + 1);
            pstmt.setInt(7, reSeq + 1);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 조회수 업데이트
    public int updateCount(int bno) {
        con = getConnection();
        int result = 0;
        String sql = "UPDATE BOARD SET READ_COUNT = READ_COUNT + 1 WHERE BNO = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bno);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    public List<BoardDto> getSearchList(SearchDto searchDto) {
        con = getConnection();
        String sql = "SELECT BNO ,TITLE ,NAME ,REGDATE ,READ_COUNT ,RE_LEV ";
        // 필드명은 ? 처리 불가, 값만 가능
        // sql문 띄어쓰기 잘 보기
        sql += "FROM BOARD WHERE " + searchDto.getCriteria() + " LIKE ?";
        sql += "ORDER BY RE_REF DESC, RE_SEQ";
        List<BoardDto> list = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + searchDto.getKeyword() + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setReadCount(rs.getInt(5));
                dto.setReLev(rs.getInt(6));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(Connection con, PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
