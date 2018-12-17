/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventorysystem;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 *
 * @author khamx
 */
public class allTools {
    public void clearscreen(){
        
        try 
        {
            if (System.getProperty("os.name").contains("Window"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");

        }
        catch(IOException | InterruptedException ex ) 
        {

        }
      
    }
    
    public void DisplayMainMenu()
    {
        System.out.println("1. Add new product");
        System.out.println("2. Modify product details");
        System.out.println("3. Delete product details");
        System.out.println("4. Display product details based on product code");
        System.out.println("5. Display product details based on product name");
        System.out.println("6. Display inventory");
        System.out.println("7. Exit");
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
                    mess = "The code must be unique";
                    break;
                }
            }
            if(!Pattern.matches("^[a-zA-Z0-9_]*$", pcode))
            {
                res = false;
                mess = "Characters must be letters and numbers or underline characters";
            }
            if(pcode.length() > 5)
            {
                res = false;
                mess = "Length code must be less than or equal to 5";
            }
            if(pcode.equals(""))
            {
                res = false;
                mess = "Please enter product code"; 
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
            System.out.println("Name length must be less than 30 characters");
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
                System.out.println("Value must be between 1,000,000 and 9,999,999");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Value must be a digit");
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
                System.out.println("Value must be between 0 to 1000");
            }
        }
        catch(Exception ex)
        {
            res = false;
            System.out.println("Value must be a digit");
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
                System.out.println("Number can not less than 0");
            }
        }
        catch(Exception ex)
        {
            res = false;
            System.out.println("Value must be a digit");
        }
        return res;
    }
    
    public boolean checkCodeDel(String pcode)
    {
        boolean res = true;
        String mess = "";
        if(!Pattern.matches("^[a-zA-Z0-9_]*$", pcode))
            {
                res = false;
                mess = "Characters must be letters and numbers or underline characters";
            }
            if(pcode.length() > 5)
            {
                res = false;
                mess = "Length code must be less than or equal to 5";
            }
            if(pcode.equals(""))
            {
                res = false;
                mess = "Please enter product code"; 
            }
            if(res == false)
        {
            System.out.println(mess);
        }
        return res;
    }
}
