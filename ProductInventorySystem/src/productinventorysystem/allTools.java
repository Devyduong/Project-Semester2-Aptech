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
    public static void clearscreen(){
        
    try {
        if (System.getProperty("os.name").contains("Window"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
        
    }catch(IOException | InterruptedException ex ) {}
    }
}
