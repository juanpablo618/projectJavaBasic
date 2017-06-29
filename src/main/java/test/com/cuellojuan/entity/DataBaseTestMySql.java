package test.com.cuellojuan.entity;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DataBaseTestMySql {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/concretepage";
    private final static String USER = "root";
    private final static String PASS = "";




    @Test
    public void testConnectionToMySQL(){
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
                    //Comparo el nombre de la bd
                    assertEquals("MySQL", conn.getMetaData().getDatabaseProductName());
                    conn.close();
               } catch (Exception e) {

                }
            }
        }

    }

    @Test(expected = java.sql.SQLException.class)
    public void testConnectionfailInvalidPassword() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        Class.forName("org.apache.commons.dbcp.BasicDataSource");
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,"blabla");
        assertEquals(null,conn);
        conn.close();

    }



}
