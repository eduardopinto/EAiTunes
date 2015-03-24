
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
public class Music extends Content {
    
    private int duration;

    public Music(int duration, String publisher, String name, float price) throws Exception {
        super(publisher, name, price);
        this.setDuration(duration);
    }

    public int getDuration() {
        return duration;
    }

    public final void setDuration(int duration) {
        this.duration = duration;
    } 
}
