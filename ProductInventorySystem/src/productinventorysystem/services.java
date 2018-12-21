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
   ProductInventorySystem objMain = new ProductInventorySystem();
   Additional objAdd = new Additional();
   
   Scanner ip = new Scanner(System.in);
   
   public void connectDatabase() throws SQLException
   {
       conn = DriverManager.getConnection(DbUrl + "; databaseName =" + databaseName + ";",username,passw);

       if(conn == null)
       {
           System.out.println(">>>>>We have some problem when try connect to database!!!");
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
           System.out.println("----------------------ADD NEW PRODUCT----------------------");
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
           System.out.println(">>>>>Add new product details successful");
       }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
   }
   
   /**
    * modify product information
    */
   public void ModifyProduct()
   {
        String product_name, description;
        String unit_price = "0f";
        String amount, number;
       try
       {
            services obj = new services();
           ResultSet rs;
           Statement stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM tbl_product");//lay danh sach toan bo san pham trong csdl
            String prid = "";
            do
            {
                System.out.println("Enter product code you wanna modify: ");//nhap ma san pham de xoa
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
                   //System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
                   //System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)+" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));
                   objAdd.printTableHeader();
                   objAdd.printTableBody(rs.getString(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(6), rs.getString(5));
               }
           }
           // ket thuc 
           if(ktt == false)
           {
               System.out.println(">>>>>Not found any product which has that code");
           }
           else
           {
               //neu ton tai san pham
               System.out.println("<<<<<Are you sure want to edit this product? (Y / N)>>>>>");
               String ch = ip.next();
               if(ch.toUpperCase().equals("Y"))
               {
                   System.out.println("Enter new information of product :" + prid);
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
                    String cmd = "UPDATE tbl_product SET product_name = ?"
                            + ", unit_price = ?, amount = ?, descript = ?, numbers = ? WHERE product_code = ?";
                    
                     PreparedStatement stmt = conn.prepareStatement(cmd);
                     stmt.setString(1, product_name);
                     stmt.setString(2, unit_price);
                     stmt.setString(3, amount);
                     stmt.setString(4, description);
                     stmt.setString(5, number);
                     stmt.setString(6, prid);
                     stmt.executeUpdate();
                     System.out.println(">>>>>Updated successful");
               }
               else
               {
                   // xoa man hinh va tro ve menu hoac list
               }
           }
       }
       catch(Exception ex)
       {
           
       }
   }
   /**
    * @method deleteProduct
    */
   public void DeleteProduct()
   {
       services objService = new services();
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
                   //System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
                   //System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)+" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));
                   objAdd.printTableHeader();
                   objAdd.printTableBody(rs.getString(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(6), rs.getString(5));
               }
           }
           // ket thuc 
           if(ktt == false)
           {
               System.out.println(">>>>>Not found any product which has that code");
           }
           else
           {
               //neu ton tai san pham
               System.out.println("<<<<<Are you sure want to remove this product? (Y / N)>>>>>");
               String ch = ip.next();
               if(ch.toUpperCase().equals("Y"))
               {
                   String cmd = "DELETE FROM tbl_product WHERE product_code = '"+prid+"'";
                   PreparedStatement pstt = conn.prepareStatement(cmd);
                   pstt.execute();
                   System.out.println(">>>>>Deleted");
               }
               else
               {
                   //objService.DisplayInventory();
                   //objTool.DisplayMainMenu();
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
       System.out.println("    ========================== DISPLAY PRODUCT DETAIL BASE ON CODE ==========================");
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
                   //System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
                   objAdd.printTableHeader();
                   //System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)+" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));
                   objAdd.printTableBody(rs.getString(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(6), rs.getString(5));
               }
           }
           // ket thuc 
           if(ktt == false)
           {
               System.out.println(">>>>>Not found any product has this code: "+prid);
           }
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
   }
   /**
    * Display product base on name
    * @method displayBaseOnName
    */
   public void displayBaseOnName()
   {
       System.out.println("    ========================== DISPLAY PRODUCT DETAIL BASE ON NAME ==========================");
       try
       {
           boolean kt = false;
           System.out.println("Enter product name: ");
           String product_name = ip.nextLine();
           //product_name = ip.nextLine();
           
           ResultSet rs;
           Statement stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM tbl_product");//lay danh sach toan bo san pham trong csdl
           //System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
           objAdd.printTableHeader();
           while(rs.next())
           {
               if(rs.getString(2).toUpperCase().equals(product_name.toUpperCase()))
               {
                   kt = true;
                   //System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)+" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));
                   objAdd.printTableBody(rs.getString(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(6), rs.getString(5));
               }
           }
           if(kt == false)
           {
               System.out.println(">>>>>Not found");
           }
                   
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
   }
   /**
    * @method DisplayInventory 
    * Display all products
    * @return void
    * @throws SQLException 
    */
   public void DisplayInventory() throws SQLException
   {
       try
       {  
           services objSer = new services();
            Statement stt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs =  stt.executeQuery("SELECT * FROM tbl_product");
             int totalLines = 0; //tong so line co trong ResultSet
             int totalPages; // Tong so trang
             int currentRow = 0; // line hien thoi trong resultset 
             int currentPage = 1;
             int numberLinesEachPage = 5;
            while(rs.next())
            {
                totalLines++;
            }
            totalPages = (int)(totalLines / numberLinesEachPage) + 1; // tinh tong so trang = (tong so dong / so dong tren 1 trang) + 1;
             
            rs.beforeFirst(); //chuyen con tro ve vi tri dau ban ghi
            boolean clAll = true; // bien kiem tra co tiep tuc xu li o function nay nua hay ko
            do
            {
                rs.beforeFirst(); //chuyen con tro ve vi tri dau ban ghi
                rs.absolute(currentRow); //chuyen con tro den line hien tai
                System.out.println("    ================================== ALL PRODUCT INVENTORY ==================================");
                System.out.println("Total Pages: " + totalPages+ " - This is page "+ currentPage+"\n"); // in ra thong bao co bao nhieu trang - This is page 1
                //System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
                objAdd.printTableHeader();
                //<in ra page>
                for(int i = currentRow; i < currentPage * numberLinesEachPage ; i++)
                {
                    if(rs.next()) 
                    {
                        //System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)
                        //    +" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));
                        objAdd.printTableBody(rs.getString(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(6), rs.getString(5));
                    }
                }
                //</in ra page>
                currentRow = currentPage * numberLinesEachPage; // reset line hien thoi
                String cho = objTool.subMenuDisplayAll(); // 1. Home 2. Search 3. other pages
                if(cho.equals("1"))// comback home
                {
                    objTool.DisplayMainMenu();
                    clAll = false;
                }
                else if(cho.equals("2")) //search 
                {
                    try
                    {
                        Scanner sip = new Scanner(System.in);
                        System.out.print("Enter keyword to search:");
                        String keyword = sip.nextLine(); // nhap tu tim kiem
                        //objSer.SearchProduct(keyword);

                        String cmman = "SELECT * FROM tbl_product WHERE product_name LIKE LOWER('%"+keyword+"%')"; //cau lenh SQL
                        ResultSet rest = stt.executeQuery(cmman); //thưc thi truy van
                        if(!rest.wasNull()) // neu tim thay
                        {
                            //System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
                            objAdd.printTableHeader();
                        }
                        else // neu khong tim thay
                        {
                            System.out.println(">>>>> Not found any product");
                        }
                        while(rest.next()) // inn ra
                        {
//                             System.out.println(rest.getString(1)+" \t"+rest.getString(2)+" \t"+rest.getString(3)
//                                     +" \t"+rest.getString(4)+" \t"+rest.getString(5)+" \t"+rest.getString(6));
                            objAdd.printTableBody(rest.getString(1), rest.getString(2), rest.getString(3),
                                rest.getString(4), rest.getString(6), rest.getString(5));
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(">>>>>"+ex.getMessage());
                    }
                    clAll = false; // ket thuc thuc thi function nay
                    objTool.DisplayMainMenu(); // tro ve trang chu
                }
                else if(cho.equals("3")) // other pages
                {
                    boolean cke = true; // bien check vòng lặp
                    String chs = "";
                    while(cke)
                    {
                        //nhập page
                        System.out.println("Enter page:(1 - "+totalPages+")");
                        chs = ip.next();

                        //kiểm tra xem có 
                        if(objTool.IsNumber(chs))
                        {
                            if(Integer.parseInt(chs) <= totalPages)
                            {
                                cke = false;
                                if(Integer.parseInt(chs) < currentPage)
                                {
                                    currentRow = (Integer.parseInt(chs) - 1) * numberLinesEachPage;
                                }
                                else if(Integer.parseInt(chs) == currentPage)
                                {
                                    System.out.println(">>>>>You are here");
                                }
                                currentPage = Integer.parseInt(chs);
                            }
                            else
                            {
                                System.out.println(">>>>>Your choice is invalid");
                            }
                        }
                        else
                        {
                            System.out.println(">>>>>Your choice is invalid");
                        }
                    }
                }
            }while(clAll);
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
   
   public void SearchProduct(String keyword)
   {
       try
       {
           //System.out.print("Enter keyword to search:");
           //String keyword = ip.nextLine();
            Statement stt = conn.createStatement();
            String cmd = "SELECT * FROM tbl_product WHERE product_name LIKE LOWER('%"+keyword+"%')";
            ResultSet rs = stt.executeQuery(cmd);
            while(rs.next())
                {
                     System.out.println("Product Code \t Product Name \t Unit price \t Amount \t Description \t Number");
                     System.out.println(rs.getString(1)+" \t"+rs.getString(2)+" \t"+rs.getString(3)
                             +" \t"+rs.getString(4)+" \t"+rs.getString(5)+" \t"+rs.getString(6));

                }
            
       }
       catch(Exception ex)
       {
           System.out.println(">>>>>"+ex.getMessage());
       }
   }
}
