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
        if (!isValidName(firstName)) {
            throw new Exception(INVALID_FIRST_NAME);
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (!isValidName(lastName)) {
            throw new Exception(INVALID_LAST_NAME);
        }
        this.lastName = lastName;
    }

    public Content getBuyById(int id) {
        if (!this.contents.isEmpty()) {
            return this.contents.get(id);
        }
        return null;
    }

    public boolean createBuy(int id, Content content) {
        boolean created = false;
        
        this.contents.put(id, content);
        
        if(contents.containsKey(id)){
            created = true;
        }
        
        return created;
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().equals("");
    }

}
