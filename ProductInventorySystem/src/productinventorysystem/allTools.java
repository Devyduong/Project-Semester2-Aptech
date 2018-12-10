/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventorysystem;

import java.io.IOException;

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
    
}
