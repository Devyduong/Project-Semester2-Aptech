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
    
}
