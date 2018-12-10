package productinventorysystem;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author DTH
 */
public class ProductInventorySystem 
{
    public static void main(String[] args) throws SQLException 
    {
        //create instances 
        services objService = new services();
        allTools objAllTool = new allTools();
        Additional objAdd = new Additional();
        Scanner ip = new Scanner(System.in);
        Authenticates objAuthen = new Authenticates();
        
        //objAdd.startScreen();
        objService.connectDatabase();
        //objService.DeleteProduct();
        //objService.addProduct();
        //objService.closeConnection();
        //objService.DisplayBaseOnCode();
        //objService.displayBaseOnName();
        objService.DisplayInventory();
        //objAllTool.clearscreen();
        //objService.test();
//        System.out.println("USERNAME: ");
//        String usern = ip.next();
//        
//        System.out.println("Password: ");
//        String passw = ip.next();
//        
//        if(objAuthen.Login(usern, passw))
//        {
//            System.out.println("Login successfully");
//        }
//        else
//        {
//            System.out.println("Fail");
//        }
//        objAllTool.clearscreen();
        
    }
    
}
