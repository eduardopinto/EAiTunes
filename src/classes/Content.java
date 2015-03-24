package classes;

/**
 *
 * @author Eduardo Pinto PG27732
 * @author Nuno Dionísio PG27712
 * @author Nuno Gomes PG27766
 * @author Nuno Santos PG27728
 *
 */
public abstract class Content {

    private final static String INVALID_PUBLISHER_NAME = "Invalid publisher name";
    private final static String INVALID_CONTENT_NAME = "Invalid content name";
    private final static String INVALID_PRICE = "Invalid price";

    private String publisher;
    private String name;
    private float price;

    public Content(String publisher, String name, float price) throws Exception {
        this.setPublisher(publisher);
        this.setName(name);
        this.setPrice(price);
    }

    public String getPublisher() {
        return publisher;
    }

    public final void setPublisher(String publisher) throws Exception {
        if (isValidName(publisher)) {
            this.publisher = publisher;
        } else {
            throw new Exception(INVALID_PUBLISHER_NAME);
        }
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) throws Exception {
        if (isValidName(publisher)) {
            this.name = name;
        }else{
            throw new Exception(INVALID_CONTENT_NAME);
        }    
    }

    public float getPrice() {
        return price;
    }

    public final void setPrice(float price) throws Exception {
        if (isValidPrice(price)) {
            this.price = price;
        }else{
            throw new Exception(INVALID_PRICE);
        } 
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().equals("");
    }

    private boolean isValidPrice(float price) {
        return price >= 0.0f;
    }
}
