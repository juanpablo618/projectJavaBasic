package test.com.cuellojuan.entity;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseTest {

    private final static String DB_URL = "jdbc:h2:tcp://localhost/~/testdb";
    private final static String USER = "sa";
    private final static String PASS = "";

    private static Log logger = LogFactory.getLog("com.cuellojuan.entity.business");



    @Test
    public void testConnection(){
        Connection conn = null;
        try {
            Class.forName("org.apache.commons.dbcp.BasicDataSource");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("success and close db");

                } catch (Exception e) {

                }
            }
        }

    }

    @Test(expected = org.h2.jdbc.JdbcSQLException.class)
    public void testConnectionfailInvalidPassword() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        Class.forName("org.apache.commons.dbcp.BasicDataSource");
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,"blabla");

        conn.close();

    }



}
