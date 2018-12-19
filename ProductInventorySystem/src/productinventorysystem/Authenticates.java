/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventorysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
//import javax.mail.Session;

/**
 *
 * @author DTH
 */
public class Authenticates 
{
    public boolean Login(String usern, String passw)
    {
        boolean rs = false;
        Authenticates obj = new Authenticates();
        try
        {
            ResultSet listUsers = obj.getListUsers();
            
            while(listUsers.next())
            {
                if(listUsers.getString(1).toLowerCase().equals(usern.toLowerCase())
                        && listUsers.getString(2).toLowerCase().equals(passw.toLowerCase()))
                {
                     rs = true;
                     break;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        if(rs == false)
        {
            System.out.println(">>>>>Username or password is incorrect. Please try again");
        }
        return rs;
    }
    
    public ResultSet getListUsers() throws SQLException
    {
        services obj = new services();
        obj.connectDatabase();
        return obj.getListUsers();
    }
}
