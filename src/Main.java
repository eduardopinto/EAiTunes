
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
            itunes.createUser("Nuno", "Gomes");
        } catch (Exception ex) {
            System.out.println(ex);
        }     
        
    }
    
}
