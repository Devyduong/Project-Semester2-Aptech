package productinventorysystem;

import java.sql.SQLException;

/**
 *
 * @author DTH
 */
public class ProductInventorySystem 
{
    public static void main(String[] args) throws SQLException 
    {
        services objService = new services();
        objService.connectDatabase();
    }
    
}
