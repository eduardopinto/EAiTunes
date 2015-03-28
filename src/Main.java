
import classes.Content;
import classes.User;
import content.App;
import content.Music;
import content.Video;
import itunes.DBConnector;
import itunes.EAiTunes;
import java.util.Collection;

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
        
        try
        {
        // send app creation request
        Object[] content = new Object[] { new String("Aplicacao de calculo"), new String("Microsoft"),new String("Excel"), new Float(34)};
        itunes.createContent("App", content);
        
        // send music creation request
        Object[] content2 = new Object[] { new Integer(342), new String("Royksopp"),new String("I had this thing"), new Float(2)};
        itunes.createContent("Music", content2);
        
        // send Video creation request
        Object[] content3 = new Object[] { new String("800*600"), new String("Summit Entertainment"),new String("Memento"), new Float(13)};
        itunes.createContent("Video", content3);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

        try {
            
            Collection<Content> contentsByUserId = itunes.getContentsByUserId(16);
            for (Content contentsByUserId1 : contentsByUserId) {
                System.out.println(contentsByUserId1);
            }
            
            Content contentsByUserId1 = itunes.getContentByUserId(16, 3);
        
                System.out.println(contentsByUserId1.toString());
            
            
            //System.out.println(itunes.createUser("Nuno", "Gomes"));
            //System.out.println(itunes.updateUser(15, "Nass", "Gomes"));
            //System.out.println(itunes.deleteUser(14));
            // User u = itunes.getUserById(13);
            //System.out.println(u.getFirstName());
        } catch (Exception ex) {
            System.out.println(ex);
        }     
        
        
//        try {
//            //System.out.println(itunes.createUser("Nuno", "Gomes"));
//            System.out.println(itunes.updateUser(15, "Nass", "Gomes"));
//             //System.out.println(itunes.deleteUser(14));
//           // User u = itunes.getUserById(13);
//            //System.out.println(u.getFirstName());
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }     
    }  
}
