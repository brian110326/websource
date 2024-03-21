package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.TodoDto;

public class TodoDao {
    // JDBC
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

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

    // 전체조회 - Read
    public List<TodoDto> getList() {
        con = getConnection();
        String sql = "SELECT no, title, created_at, completed FROM todotbl order by no";
        List<TodoDto> list = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TodoDto dto = new TodoDto();
                dto.setNumber(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setCreated_at(rs.getDate(3));
                dto.setCompleted(rs.getBoolean(4));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return list;
    }

    public TodoDto getRow(String no) {
        con = getConnection();
        String sql = "SELECT * FROM todotbl where no = ?";
        TodoDto dto = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(no));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto = new TodoDto();
                dto.setNumber(rs.getInt(1));
                dto.setTitle(rs.getString(2));
                dto.setCreated_at(rs.getDate(3));
                dto.setCompleted(rs.getBoolean(4));
                dto.setDescription(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return dto;
    }

    public int update(TodoDto updatedto) {
        con = getConnection();
        String sql = "UPDATE TODOTBL SET COMPLETED = ?, DESCRIPTION =? WHERE NO = ?";
        int result = 0;
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setBoolean(1, updatedto.isCompleted());
            pstmt.setString(2, updatedto.getDescription());
            pstmt.setInt(3, updatedto.getNumber());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    public int delete(String no) {
        con = getConnection();
        String sql = "DELETE FROM TODOTBL WHERE NO = ?";
        int result = 0;
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, Integer.parseInt(no));

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

    public int insert(TodoDto insertdto) {
        con = getConnection();
        String sql = "insert into todotbl(NO,TITLE,DESCRIPTION) values(todo_seq.nextval,?,?)";
        int result = 0;
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, insertdto.getTitle());
            pstmt.setString(2, insertdto.getDescription());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

}
