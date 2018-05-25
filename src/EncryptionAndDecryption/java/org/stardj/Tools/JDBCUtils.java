package org.stardj.Tools;

import org.stardj.User.iMovies;

import java.sql.*;

/**
 * Created by stardj on 16/7/22.
 */
public class JDBCUtils {

    public static final String url = "jdbc:mysql://127.0.0.1/imovie";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "tiancai520";

    public static Connection conn = null;
    public static PreparedStatement pstmt = null;


    private static Connection getConn() {

        try {
            Class.forName(name); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int insert(iMovies imovies) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into names (id,name) values(?,?)";
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(imovies.getFilenameMD5()));
            pstmt.setString(2, imovies.getFileName());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static Integer getAll() {
        Connection conn = getConn();
        String sql = "select * from names";
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String select(int ID) {
        Connection conn = getConn();
        String name = "";
        String sql = "SELECT * FROM names WHERE id='" + ID + "'";
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                name = rs.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }


    public static int delete(int ID) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from names where id='" + ID + "'";
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int update(iMovies imovies) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update names set ID='" + imovies.getFilenameMD5() + "' where name='" + imovies.getFileName() + "'";
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void main(String[] args) {

    }
}


