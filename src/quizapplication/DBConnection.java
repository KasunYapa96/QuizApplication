/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapplication;

/**
 *
 * @author helitha
 */
import java.sql.*;
public class DBConnection {
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp","root","");
            return conn;
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
}
