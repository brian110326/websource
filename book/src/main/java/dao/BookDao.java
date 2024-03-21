package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BookDto;

public class BookDao {
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
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "c##test2";
        String password = "test";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    // CRUD

    public List<BookDto> getList() {
        con = getConnection();
        String sql = "SELECT * FROM BOOKTBL ORDER BY CODE";
        List<BookDto> list = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDto dto = new BookDto();
                dto.setCode(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setWriter(rs.getString(3));
                dto.setPrice(rs.getInt(4));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    public List<BookDto> getSearchList(String criteria, String keyword) {
        con = getConnection();
        // 검색기준이 code라면
        // String sql = "SELECT * FROM BOOKTBL WHERE CODE=?";
        // 검색기준이 writer라면
        // String sql = "SELECT * FROM BOOKTBL WHERE WRITER=?";
        String sql = null;
        if (criteria.equals("code")) {
            sql = "SELECT * FROM BOOKTBL WHERE CODE=?";
        }
        if (criteria.equals("writer")) {
            sql = "SELECT * FROM BOOKTBL WHERE WRITER=?";
        }
        List<BookDto> list = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, keyword);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDto dto = new BookDto();
                dto.setCode(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setWriter(rs.getString(3));
                dto.setPrice(rs.getInt(4));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    public BookDto getRow(int code) {
        BookDto dto = new BookDto();
        con = getConnection();
        String sql = "SELECT * FROM BOOKTBL WHERE CODE = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, code);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto.setCode(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setWriter(rs.getString(3));
                dto.setPrice(rs.getInt(4));
                dto.setDescription(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return dto;
    }

    public int update(BookDto updateDto) {
        int result = 0;
        con = getConnection();

        String sql = "UPDATE BOOKTBL SET PRICE=? WHERE CODE=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, updateDto.getPrice());
            pstmt.setInt(2, updateDto.getCode());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    public int insert(BookDto insertDto) {
        int result = 0;
        con = getConnection();

        String sql = "INSERT INTO BOOKTBL VALUES(?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, insertDto.getCode());
            pstmt.setString(2, insertDto.getTitle());
            pstmt.setString(3, insertDto.getWriter());
            pstmt.setInt(4, insertDto.getPrice());
            pstmt.setString(5, insertDto.getDescription());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    public int delete(int code) {
        int result = 0;
        con = getConnection();

        String sql = "DELETE FROM BOOKTBL WHERE CODE = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, code);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
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
