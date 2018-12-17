/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventorysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Group2
 */
public class services {
   private Connection conn = null;
   private String DbUrl = "jdbc:sqlserver://localhost";
   private String username = "sa";
   private String passw = "123456";
   private String databaseName = "INVENTORYSYSTEM";
   
   allTools objTool = new allTools();
   
   Scanner ip = new Scanner(System.in);
   
   public void connectDatabase() throws SQLException
   {
       conn = DriverManager.getConnection(DbUrl + "; databaseName =" + databaseName + ";",username,passw);

       if(conn == null)
       {
           System.out.println("We have some problem when try connect to database!!!");
       }
   }    
   public void closeConnection() throws SQLException
   {
       conn.close();
   }
   /**
    *   @author hungdt
    *   @method addProduct()
    */
   public void addProduct()
   {
       try
       {
           //create variable of each fields of tbl_product
           String product_code, product_name, description;
           String unit_price = "0f";
           String amount, number;
           
           //insert product code 
           do
           {
                System.out.print("Enter product code: ");
                product_code = ip.next();
           }
           while(!objTool.checkCode(product_code));
           //end indert product code
           
           //<check value product name> 
           do
           {
                System.out.print("Enter product name: ");
                product_name = ip.nextLine();
                product_name = ip.nextLine();
           }
           while(!objTool.checkName(product_name));
           //</check value product name>
           
           //<check price>
           do
           {
                System.out.print("Enter unit price: ");
                unit_price = ip.next();
               
           }while(!objTool.checkPrice(unit_price));
           //</check price>
           
           //<check amount>
           do
           {
                System.out.print("Enter amount: ");
                amount = ip.next();
           }
           while(!objTool.checkAmount(amount));
           //</check amount>
           System.out.println("Enter description: ");
           description = ip.nextLine();
           description = ip.nextLine();
           
           //<check number>
           do
           {
                System.out.print("Enter number of product: ");
                number = ip.next();
           }
           while(!objTool.checkNumber(number));
           //</check number>
           //insert into database
           String cmd = "INSERT INTO tbl_product(product_code, product_name, unit_price, amount, descript, numbers)"
                   + " VALUES ('" + product_code + "','" + product_name + "','" + unit_price 
                   + "','" + amount + "','" + description + "','" + number + "')";
           
           Statement stt = conn.createStatement(); //create statement
           stt.executeUpdate(cmd); //execute command
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
   }
   /**
    * @method deleteProduct
    */
   public void DeleteProduct()
   {
       try
       {
           services obj = new services();
           ResultSet rs;
           Statement stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM tbl_product");//lay danh sach toan bo san pham trong csdl
            String prid = "";
            do
            {
                System.out.println("Enter product code you wanna remove: ");//nhap ma san pham de xoa
                prid = ip.next();
            }
            while(!objTool.checkCodeDel(prid));
           boolean ktt = false;//de kiem tra co ton tai san pham nay khong
           //in ra thong tin san pham neu co
           while(rs.next())
           {
               if(rs.getString(1).equals(prid))
               {
                   ktt = true;
                   System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
                   System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)+" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));
               }
           }
           // ket thuc 
           if(ktt == false)
           {
               System.out.println("Not found any product which has that code");
           }
           else
           {
               //neu ton tai san pham
               System.out.println("Are you sure want to remove this product? (Y / N)");
               String ch = ip.next();
               if(ch.toUpperCase().equals("Y"))
               {
                   String cmd = "DELETE FROM tbl_product WHERE product_code = '"+prid+"'";
                   PreparedStatement pstt = conn.prepareStatement(cmd);
                   pstt.execute();
                   System.out.println("Deleted");
               }
               else
               {
                   // xoa man hinh va tro ve list hoac menu
               }
           }
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
   }
   /**
    * @method DisplayBaseOnCode
    *  
    */
   public void DisplayBaseOnCode()
   {
       try
       {
           
           services obj = new services();
           ResultSet rs;
           Statement stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM tbl_product");//lay danh sach toan bo san pham trong csdl
           System.out.println("Enter product code: ");//nhap ma san pham
           String prid = ip.next();
           boolean ktt = false;//de kiem tra co ton tai san pham nay khong
           //in ra thong tin san pham neu co
           while(rs.next())
           {
               if(rs.getString(1).equals(prid))
               {
                   ktt = true;
                   System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
                   System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)+" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));
               }
           }
           // ket thuc 
           if(ktt == false)
           {
               System.out.println("Not found any product has this code: "+prid);
           }
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
   }
   public void displayBaseOnName()
   {
       try
       {
           boolean kt = false;
           System.out.println("Enter product name: ");
           String product_name = ip.nextLine();
           product_name = ip.nextLine();
           
           ResultSet rs;
           Statement stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM tbl_product");//lay danh sach toan bo san pham trong csdl
           System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
           while(rs.next())
           {
               if(rs.getString(2).toUpperCase().equals(product_name.toUpperCase()))
               {
                   kt = true;
                   System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)+" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));
               }
           }
           if(kt == false)
           {
               System.out.println("Not found");
           }

                   
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
   }
   public void DisplayInventory() throws SQLException
   {
       try
       {
            ResultSet rs = new services().getListProduct();
            System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
            while(rs.next())
            {
                System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)
                        +" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));   
            }
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
   }
   public ResultSet getListUsers() throws SQLException
   {
       ResultSet rs;
       Statement stt = conn.createStatement();
       String cmd = "SELECT * FROM tbl_users";
       rs = stt.executeQuery(cmd);
       return rs;
   }
   public ResultSet getListProduct() throws SQLException
   {
       ResultSet rs;
       //new services().connectDatabase();
       Statement stt = conn.createStatement();
       rs = stt.executeQuery("SELECT * FROM tbl_product");
       return rs;
   }
   public ResultSet getProducts(String product_name) throws SQLException
   {
       Statement stt = conn.createStatement();
       String cmd = "SELECT * FROM tbl_product WHERE product_name = '"+product_name+"'";
       ResultSet rs = stt.executeQuery(cmd);
       return rs;
   }
//   public void test() throws SQLException
//   {
//       services obj = new services();
//       ResultSet list = obj.getListUsers();
//       while(list.next())
//       {
//           System.out.println(list.getString(1)+" "+list.getString(2));
//       }
//   }
}
