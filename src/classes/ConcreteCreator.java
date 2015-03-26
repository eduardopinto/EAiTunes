/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import content.App;
import content.Music;
import content.Video;
/**
 *
 * @author bam
 */
public class ConcreteCreator extends Creator{
    
    @Override
    public void factoryMethod(String contentType, Object[] content)
    {
        switch(contentType)
        {   
            case "App":
                /* verify if the content is correct */
                System.out.printf("\nCriar app\n");
                System.out.printf("Details:" + content[0] + "\n");
                System.out.printf("Publisher:" + content[1] + "\n");
                System.out.printf("Name:" + content[2] + "\n");
                System.out.printf("Price:" + content[3] + "\n");
                //return new App(content[0],content[1],content[2],content[3]);
                break;
            case "Music":
                //return new Music(content[0],content[1],content[2],content[3]);
                System.out.printf("Cria Musica");
                break;
            case "Video":
                System.out.printf("Cria Video");
                //return new Video(content[0],content[1],content[2],content[3]);
                break;
        }
    }
}
