package classes;

import content.App;
import content.Music;
import content.Video;

public class ConcreteCreator extends Creator {

    private final static String INVALID_TYPE = "Invalid content type";

    @Override
    public Content factoryMethod(String contentType, Object[] contents) throws Exception {
        Content content = null;
        switch (contentType) {
            case "App": {
                content = new App((String) contents[0], (String) contents[1], (String) contents[2], (float) contents[3]);
                break;
            }
            case "Music": {
                content = new Music((int) contents[0], (String) contents[1], (String) contents[2], (float) contents[3]);
                break;
            }
            case "Video": {
                content = new Video((String) contents[0], (String) contents[1], (String) contents[2], (float) contents[3]);
                break;
            }
            default: {
                throw new Exception(INVALID_TYPE);
            }
        }
        return content;
    }
}
