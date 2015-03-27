
import classes.User;
import itunes.DBConnector;
import itunes.EAiTunes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nunoandrebarbosagomes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConnector db = new DBConnector();
        EAiTunes itunes = new EAiTunes(db);
        
        try {
            itunes.getUserById(16).getAllVideosByUser(db);
            
            
            //System.out.println(itunes.createUser("Nuno", "Gomes"));
            //System.out.println(itunes.updateUser(15, "Nass", "Gomes"));
             //System.out.println(itunes.deleteUser(14));
           // User u = itunes.getUserById(13);
            //System.out.println(u.getFirstName());
        } catch (Exception ex) {
            System.out.println(ex);
        }     
        
    }
    
}
