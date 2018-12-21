/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventorysystem;

/**
 *
 * @author DTH
 */
public class Additional
{
     int totallength = 157;
     private String welcome = "WELCOME TO PRODUCT INVENTORY SYSTEM";
    public void startScreen()
    {
        Additional obj = new Additional();
        
        String bor = "=============================================================================================================================="
                + "===============================";
        //System.out.println(a.length());
        System.out.println(bor);
        obj.writeSpace(totallength - 4, true, true);
        obj.writeSpace(45, true, false);
        System.out.print(welcome);
        obj.writeSpace((totallength - 45 - welcome.length() - 4), false, true);
        for(int i = 1; i <= 25; i++)
        {
            obj.writeSpace(totallength - 4, true, true);
        }
        System.out.println(bor);
    }
    
    public void writeSpace(int lengths, boolean ktb, boolean kta)
    {
        if(ktb) System.out.print("||");
        for(int i = 1; i <= lengths; i++) System.out.print(" ");
        if(kta) System.out.println("||");
    }
    /**
     * this is welcome screen
     * It is played if login successful
     * @author DTH
     */
    public void startSc()
    {
        System.out.println("======================="+welcome+"=======================");
        System.out.println("|| Welcome to Koubai System, this system is used  to manage products inventory.||");
        System.out.println("|| You will perform the function by following the instructions of each function||");
        System.out.println("|| Here are the functions of the system, to use you enter the sequence number  ||");
        System.out.println("|| of the function and press enter.                                            ||");
        System.out.println("=================================================================================");
    }
    /**
     * This function print table header when show list product
     */
    public void printTableHeader()
    {
        System.out.println("+--------------+------------------------+------------+--------+----------+-------------------------+");
        System.out.println("| Product Code |      Product Name      | Unit Price | Amount |  Number  |       Description       |");
        System.out.println("+--------------+------------------------+------------+--------+----------+-------------------------+");
    }
    /**
     * 
     * @param product_code
     * @param product_name
     * @param unit_price
     * @param amount
     * @param number
     * @param descript 
     * 
     * print border for body
     */
    public void printTableBody(String product_code, String product_name, String unit_price, String amount, String number, String descript)
    {
        //print product code
        System.out.print("|   "+product_code); //left border
        for(int i = 1; i < 15 - product_code.length() - 3; i++)
        {
            System.out.print(" ");
        } 
        System.out.print("|"); // right border
        //kt print product code
        
        //<print product name>
        System.out.print(" "+ product_name);
        for(int i = 1; i < 25 - product_name.length() - 1; i++)
        {
            System.out.print(" ");
        }
        System.out.print("|");
        //</print product name>
        
        //<print unit price>
        System.out.print("  "+unit_price+" |");
        //</print unit price>
        
        //<print amount>
        System.out.print("  "+amount);
        for(int i = 1; i < 9 - amount.length() - 2; i++)
        {
            System.out.print(" ");
        }
        System.out.print("|");
        //</print amount>
        
        //<print number>
        System.out.print("  "+number);
        for(int i = 1; i < 11 - number.length() - 2; i++)
        {
            System.out.print(" ");
        }
        System.out.print("|");
        //</print number>
        
        //<print description>
        System.out.print(descript);
        if(descript.length() <= 26)
        {
            for(int i = 1; i < 26 - descript.length(); i++)
            {
                System.out.print(" ");
            }
        }
        System.out.println("|");
        //</print description>
        
        //print end border
        System.out.println("+--------------+------------------------+------------+--------+----------+-------------------------+");
    }
}
