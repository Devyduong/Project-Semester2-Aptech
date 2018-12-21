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
        
        
        objService.connectDatabase();
        //objService.DeleteProduct();
        
        //objService.closeConnection();
        //objService.DisplayBaseOnCode();
        //objService.displayBaseOnName();
        //objService.DisplayInventory();
        //objAllTool.clearscreen();
        //objService.test();
        String usern = "";
        String passw = "";
        do
        {
            System.out.print("USERNAME: ");
            usern = ip.next();

            System.out.print("PASSWORD: ");
            passw = ip.next();
        }
        while(!objAuthen.Login(usern, passw));
        if(objAuthen.Login(usern, passw))
        {
            System.out.println(">>>>>Login successfully");
            objAdd.startSc();
            objAllTool.DisplayMainMenu();
            
            while(true)
            {
            //<Kiem tra gia tri lua chon>
            String c = "";
            boolean checkOption = true;
            while(checkOption)
            {
                System.out.print("Enter your choose: ");
                c = ip.next();
                if(c.equals("1") || c.equals("2") || c.equals("3") || c.equals("4") || c.equals("5") || c.equals("6") || c.equals("7"))
                {
                    checkOption = false;
                }
                else
                {
                    System.out.println(">>>>>Your choose is invalid. Please try again");
                }
            }
            //</Kiem tra gia tri lua chon>
            
            String chk;
            switch(c)
            {
                case "1": // Them san pham moi
                    chk = "";
                    do
                    {
                        objAllTool.clearscreen(); //xoa man hinh
                        objService.addProduct(); //thuc hien chuc nang
                        chk = objAllTool.subMenu();//tiep tuc or stop
                    }while(chk.equals("2"));
                    objAllTool.clearscreen(); // clear man hinh
                    objAllTool.DisplayMainMenu(); // hien thi menu
                    break;
                case "2": // Chinh sua thong tin san pham
                    System.out.println("    ================================== MODIFY PRODUCT DETAIL ==================================");
                    chk = "";
                    do
                    {
                        objAllTool.clearscreen(); //xoa man hinh
                        objService.ModifyProduct();// thuc hien chuc nang chinh sua
                        chk = objAllTool.subMenu();//xac nhan lua chon co tiep tuc hay dung lai
                    }while(chk.equals("2"));
                    
                    objAllTool.clearscreen();//clear man hinh
                    objAllTool.DisplayMainMenu(); // hien thi menu
                    break;
                case "3": // Xoa san pham
                    System.out.println("    ================================== DELETE PRODUCT DETAIL ==================================");
                    chk = "";
                    do
                    {
                        objAllTool.clearscreen(); //xoa man hinh
                        objService.DeleteProduct();
                        chk = objAllTool.subMenu();//xac nhan lua chon co tiep tuc hay dung lai
                    }while(chk.equals("2"));
                    
                    objAllTool.clearscreen();//clear man hinh
                    objAllTool.DisplayMainMenu(); // hien thi menu
                    break;
                case "4":// Hien thi theo code
                    chk = "";
                    objAllTool.clearscreen();//clear man hinh
                    do
                    {
                        objService.DisplayBaseOnCode();
                        chk = objAllTool.subMenuDisplay();
                    }while(chk.equals("2"));
                    if(chk.equals("1"))
                    {
                        objAllTool.clearscreen(); //xoa man hinh
                        objAllTool.DisplayMainMenu();
                    }
                    else if(chk.equals("3"))
                    {
                        objService.ModifyProduct();
                        objAllTool.DisplayMainMenu(); // hien thi menu
                    }
                    else 
                    {
                        objService.DeleteProduct();
                        objAllTool.DisplayMainMenu(); // hien thi menu
                    }
                    break;
                case "5":// Hien thi theo ten
                    chk = "";
                    objAllTool.clearscreen();//clear man hinh
                    do
                    {
                        objService.displayBaseOnName();
                        chk = objAllTool.subMenuDisplay();
                    }while(chk.equals("2"));
                    if(chk.equals("1"))
                    {
                        objAllTool.clearscreen(); //xoa man hinh
                        objAllTool.DisplayMainMenu();
                    }
                    else if(chk.equals("3"))
                    {
                        objService.ModifyProduct();
                        objAllTool.clearscreen(); //xoa man hinh
                        objAllTool.DisplayMainMenu(); // hien thi menu
                    }
                    else 
                    {
                        objService.DeleteProduct();
                        objAllTool.clearscreen(); //xoa man hinh
                        objAllTool.DisplayMainMenu(); // hien thi menu
                    }
                    break;
                case "6": // Hien thi tat ca
                    
                    objService.DisplayInventory();
                    break;
                case "7":// thoat
                    System.out.println(">>>>>GOODBYE AND SEE YOU LATER");
                    System.exit(0);
                    break;
                default:
                    break;
            }
            }
        }
        else
        {
            System.out.println("Login fail");
        }
//        objAllTool.clearscreen();
        
        
            
    }
    
}
