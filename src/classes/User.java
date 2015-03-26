package classes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eduardo Pinto PG27732
 * @author Nuno Dionísio PG27712
 * @author Nuno Gomes PG27766
 * @author Nuno Santos PG27728
 *
 */
public final class User {

    private final static String INVALID_FIRST_NAME = "Invalid first name";
    private final static String INVALID_LAST_NAME = "Invalid last name";

    private String firstName;
    private String lastName;
    private Map<Integer, Content> contents;

    public User(String firstName, String lastName) throws Exception {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.contents = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (isValidName(firstName)) {
            this.firstName = firstName;
        }else{
            throw new Exception(INVALID_FIRST_NAME);
        }
        
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (isValidName(lastName)) {
            this.lastName = lastName;
        } else {
            throw new Exception(INVALID_LAST_NAME);
        }
    }

    public Content getBuyById(int id) {
        Content content = null;

        if (this.contents.isEmpty()) {
            content = this.contents.get(id);
        }
        return content;
    }

    public boolean createBuy(int id, Content content) {
        boolean created = true;
        this.contents.put(id, content);

        if (contents.containsKey(id)) {
            created = true;
        }
        return created;
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().equals("");
    }
}
