
package content;

import classes.Content;

/**
 *
 * @author Eduardo Pinto PG27732
 * @author Nuno Dionísio PG27712
 * @author Nuno Gomes PG27766
 * @author Nuno Santos PG27728
 * 
 */
public class Video extends Content {
    
    private String resolution;

    public Video(String resolution, String publisher, String name, float price) throws Exception {
        super(publisher, name, price);
        this.setResolution(resolution);
    }

    public String getResolution() {
        return resolution;
    }

    public final void setResolution(String resolution) {
        this.resolution = resolution;
    } 
}
