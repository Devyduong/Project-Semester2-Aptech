/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventorysystem;

import java.io.Console;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author khamx
 */
public class allTools {
    public void clearscreen(){
        
//        try 
//        {
//            if (System.getProperty("os.name").contains("Window"))
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            else
//                Runtime.getRuntime().exec("clear");
//
//        }
//        catch(IOException | InterruptedException ex ) 
//        {
//
//        }
//c2
//      System.out.print("\033[H\033[2J");  
//      System.out.flush();  
//        char esc = 27; 
//        String clear = esc + "[2J"; 
        System.out.print("\f");
        
    }
    
    public void DisplayMainMenu()
    {
        System.out.println("============================================= MAIN MENU ============================================");
        System.out.println("|| 1. Add new product                           2. Modify product details                         ||");
        System.out.println("||                                                                                                ||");
        System.out.println("|| 3. Delete product details                    4. Display product details based on product code  ||");
        System.out.println("||                                                                                                ||");
        System.out.println("|| 5. Display product details based on product name  6. Display inventory                         ||");
        System.out.println("||                                                                                                ||");
        System.out.println("|| 7. Logout and Exit                                                                             ||");
        System.out.println("====================================================================================================");
    }
    
    /**
     * kiểm tra product code có duy nhất hay không
     * @author hungdt
     * @return res
     * @exception SQLException
     * 
     */
    public boolean checkCode(String pcode) 
    {
        boolean res = true;
        String mess = "";
        try
        {
            services objServices = new services();
            objServices.connectDatabase();
            ResultSet rs = objServices.getListProduct();
            while(rs.next())
            {
                if(rs.getString(1).equals(pcode))
                {
                    res = false;
                    mess = ">>>>>This code is exist. Please enter other";
                    break;
                }
            }
            if(!Pattern.matches("^[a-zA-Z0-9_]*$", pcode))
            {
                res = false;
                mess = ">>>>>Characters must be letters and numbers or underline characters";
            }
            if(pcode.length() > 5)
            {
                res = false;
                mess = ">>>>>Length code must be less than or equal to 5";
            }
            if(pcode.equals(""))
            {
                res = false;
                mess = ">>>>>Please enter product code"; 
            }
            
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        if(res == false)
        {
            System.out.println(mess);
        }
        return res;
    }
    /**
     * 
     * @param pname
     * @return res, message
     */
    public boolean checkName(String pname)
    {
        boolean res = true;
        if(pname.length() > 30)
        {
            res = false;
        }
        if(res == false)
        {
            System.out.println(">>>>>Name length must be less than 30 characters");
        }
        return res;
    }
    /**
     * 
     * @param price
     * @return res, message
     */
    public boolean checkPrice(String price)
    {
        boolean res = true;
        try
        {
            if((Float.parseFloat(price) < 1000000) || (Float.parseFloat(price) > 9999999))
            {
                res = false;
            }
            if(res == false)
            {
                System.out.println(">>>>>Value must be between 1,000,000 and 9,999,999");
            }
        }
        catch(Exception ex)
        {
            System.out.println(">>>>>Value must be a digit");
            res = false;
        }
        return res;
    }
    
    /**
     * 
     * @param amount
     * @return res, message
     */
    public boolean checkAmount(String amount)
    {
        boolean res = true;
        try
        {
            if((Integer.parseInt(amount) < 0) || (Integer.parseInt(amount) > 1000))
            {
                res = false;
            }
            if(res == false)
            {
                System.out.println(">>>>>Value must be between 0 to 1000");
            }
        }
        catch(Exception ex)
        {
            res = false;
            System.out.println(">>>>>Value must be a digit");
        }
        return res;
    }
    /**
     * 
     * @param number
     * @return res
     * @show message
     */
    public boolean checkNumber(String number)
    {
        boolean res = true;
        try
        {
            if(Integer.parseInt(number) < 0)
            {
                res = false;
                System.out.println(">>>>>Number can not less than 0");
            }
        }
        catch(Exception ex)
        {
            res = false;
            System.out.println(">>>>>Value must be a digit");
        }
        return res;
    }
    /**
     * 
     * @param pcode
     * @return 
     */
    public boolean checkCodeDel(String pcode)
    {
        boolean res = true;
        String mess = "";
        if(!Pattern.matches("^[a-zA-Z0-9_]*$", pcode))
            {
                res = false;
                mess = ">>>>>Characters must be letters and numbers or underline characters";
            }
            if(pcode.length() > 5)
            {
                res = false;
                mess = ">>>>>Length code must be less than or equal to 5";
            }
            if(pcode.length() == 0)
            {
                res = false;
                mess = ">>>>>Please enter product code"; 
            }
            if(res == false)
        {
            System.out.println(mess);
        }
        return res;
    }
    
    /**
     * subMenu for delete, modify, .etc
     * @return ch 
     */
    public String subMenu()
    {
        Scanner ip = new Scanner(System.in);
        allTools obj = new allTools();
        System.out.println("+--------------------------------------- MENU OPTIONS ---------------------------------------------+");
        System.out.println("|                    1. Home                                     2. Continue this task             |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        String ch = "";
        boolean checkOp = true;
        while(checkOp)
        {
            System.out.print("Enter your choice: ");
            ch = ip.next();
                        
            if(ch.equals("1") || ch.equals("2"))
            {
                checkOp = false;
            }
            else
            {
                System.out.println(">>>>>Your choice is invalid");
            }
        }
        return ch;
    }
    /**
     * subMenuDisplay for search by name or code
     * @return String ch
     */
    public String subMenuDisplay()
    {
        Scanner ip = new Scanner(System.in);
        allTools obj = new allTools();
        System.out.println("+--------------------------------------- MENU OPTIONS ---------------------------------------------+");
        System.out.println("|        1. Home           2. Search other products          3. Edit             4. Delete         |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        String ch = "";
        boolean checkOp = true;
        while(checkOp)
        {
            System.out.print("Enter your choice: ");
            ch = ip.next();
                        
            /**
             * neu du lieu nhap vao ma trung voi mot trong cac option 
             * thi cham dut hoat dong chon
             */
            if(ch.equals("1") || ch.equals("2") || ch.equals("3") || ch.equals("4")) 
            {
                checkOp = false;
            }
            else
            {
                //bao loi khong hop le 
                System.out.println(">>>>>Your choice is invalid"); 
            }
        }
        return ch;
    }
    public String subMenuDisplayAll()
    {
        Scanner ip = new Scanner(System.in);
        allTools obj = new allTools();
        System.out.println("+--------------------------------------- MENU OPTIONS ---------------------------------------------+");
        System.out.println("|       1. Home                  2. Search other products                  3. Go to page           |");
        System.out.println("+--------------------------------------------------------------------------------------------------+");
        String ch = "";
        boolean checkOp = true;
        while(checkOp)
        {
            System.out.print("Enter your choice: ");
            ch = ip.next();
                        
            /**
             * neu du lieu nhap vao ma trung voi mot trong cac option 
             * thi cham dut hoat dong chon
             */
            if(ch.equals("1") || ch.equals("2") || ch.equals("3")) 
            {
                checkOp = false;
            }
            else
            {
                //bao loi khong hop le 
                System.out.println(">>>>>Your choice is invalid"); 
            }
        }
        return ch;
    }
    /**
     * 
     * @param num
     * @return true/false
     */
    public boolean IsNumber(String num)
    {
        double k;
        boolean rs = true;
        try
        {
            k = Double.parseDouble(num);
        }
        catch(Exception e)
        {
            rs = false;
        }
        return rs;
    }
    
    public boolean checkNullName(String name)
    {
        boolean rs = false;
        name = name.toLowerCase();
        for (int i = 0; i < name.length(); i++) {
            for(char c = 'a'; c < 'z'; c++)
            {
                if(c == name.charAt(i))
                {
                    rs = true;
                    break;
                }
            }
        }
        if(!rs)
        {
            System.out.println(">>>>>Product name is not null");
        }
        return rs; 
    }
}
