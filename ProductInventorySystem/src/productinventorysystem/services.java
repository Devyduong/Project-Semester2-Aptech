/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventorysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author khamx
 */
public class services {
   private Connection conn = null;
   private String DbUrl = "jdbc:sqlserver://localhost";
   private String username = "sa";
   private String passw = "123456";
   private String databaseName = "INVENTORYSYSTEM";
   
   public void connectDatabase() throws SQLException
   {
       conn = DriverManager.getConnection(DbUrl + "; databaseName =" + databaseName + ";",username,passw);
       if(conn != null)
       {
           System.out.println("Connected!!!");
       }
   }    
   public void addProduct()
   {
       
   }
}
