
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
public class App extends Content {
    
    private String details;

    public App(String details, String publisher, String name, float price) throws Exception {
        super(publisher, name, price);
        this.setDetails(details);
    }

    public String getDetails() {
        return details;
    }

    public final void setDetails(String details) {
        this.details = details;
    }    
}
