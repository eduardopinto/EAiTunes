package classes;
import content.App;
import content.Music;
import content.Video;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcreteCreator extends Creator{
    
    @Override
    public Content factoryMethod(String contentType, Object[] content)
    {
        switch(contentType)
        {   
            case "App":
            {
                System.out.printf("Cria App");
                try {
                    return new App(content[0].toString(),content[1].toString(),content[2].toString(),(float)content[3]);
                } catch (Exception ex) {
                    return null;
                }
            }
            case "Music":
            {
                System.out.printf("Cria Musica");
                try {
                    return new Music((int)content[0],content[1].toString(),content[2].toString(),(float)content[3]);
                } catch (Exception ex) {
                    return null;
                }
            }
            case "Video":
            {
                System.out.printf("Cria Video");
                try {
                    return new Video(content[0].toString(),content[1].toString(),content[2].toString(),(float)content[3]);
                } catch (Exception ex) {
                    return null;
                }
            }
        }
        return null;
    }
}
