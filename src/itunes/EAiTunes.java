package itunes;

import classes.ConcreteCreator;
import classes.Content;
import classes.User;
import content.App;
import content.Music;
import content.Video;
import java.util.Collection;
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
public class EAiTunes {

    private final static String DATABASE_ERROR = "Cannot connect the database!";

    private Map<Integer, User> users;
    private Map<Integer, Content> contents;
    private DBConnector databaseConnection;

    public EAiTunes(DBConnector connector) {
        this.databaseConnection = connector;
        this.users = new HashMap<>();
        this.contents = new HashMap<>();
    }

    //users
    public User getUserById(int userId) throws Exception {
        User user = null;
        if (this.users.containsKey(userId)) {
            user = this.users.get(userId);
        } else {
            if (databaseConnection.isConnected()) {
                try {
                    User tmp = new User(userId);
                    if (tmp.load(databaseConnection)) {
                        this.users.put(tmp.getUserId(), tmp);
                        user = tmp;
                    }
                } catch (Exception e) {
                    throw new Exception(e);
                }
            } else {
                throw new Exception(DATABASE_ERROR);
            }
        }
        return user;
    }

    public boolean createUser(String firstName, String lastName) throws Exception {
        boolean added = false;
        if (databaseConnection.isConnected()) {
            try {
                User newUser = new User(firstName, lastName);
                if (newUser.persist(databaseConnection)) {
                    this.users.put(newUser.getUserId(), newUser);
                    added = true;
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return added;
    }

    public boolean deleteUser(int userId) throws Exception {
        boolean deleted = false;
        if (databaseConnection.isConnected()) {
            try {
                loadUser(userId);
                if (this.users.get(userId).delete(databaseConnection)) {
                    this.users.remove(userId);
                    deleted = true;
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return deleted;
    }

    public boolean updateUser(int userId, String firstName, String lastName) throws Exception {
        boolean updated = false;
        if (databaseConnection.isConnected()) {
            try {
                loadUser(userId);
                this.users.get(userId).setFirstName(firstName);
                this.users.get(userId).setLastName(lastName);
                if (this.users.get(userId).update(databaseConnection)) {
                    updated = true;
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return updated;
    }

    private void loadUser(int userId) throws Exception {
        if (!this.users.containsKey(userId)) {
            this.getUserById(userId);
        }
    }

    private void loadContent(String contentType, int contentId) throws Exception {
        if (!this.contents.containsKey(contentId)) {
            switch (contentType) {
                case "App":
                    this.getAppById(contentId);
                    break;
                case "Music":
                    this.getMusicById(contentId);
                    break;
                case "Video":
                    this.getVideoById(contentId);
                    break;
            }
        }
    }

    /*
     * Create Content
     */
    public boolean createContent(String contentType, Object[] contents) throws Exception {
        boolean added = false;
        if (databaseConnection.isConnected()) {
            try {
                ConcreteCreator factory = new ConcreteCreator();
                Content content = factory.factoryMethod(contentType, contents);
                if (content != null && content.persist(databaseConnection)) {
                    this.contents.put(content.getContentId(), content);
                    added = true;
                } else {
                    throw new Exception("Cannot create content type");
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return added;
    }

    //music
    public Content getMusicById(int musicId) throws Exception {
        Content app = null;
        if (this.contents.containsKey(musicId)) {
            app = this.contents.get(musicId);
        } else {
            if (databaseConnection.isConnected()) {
                try {
                    Content tmp = new Music(musicId);
                    if (tmp.load(databaseConnection)) {
                        this.contents.put(tmp.getContentId(), tmp);
                        app = tmp;
                    }
                } catch (Exception e) {
                    throw new Exception(e);
                }
            } else {
                throw new Exception(DATABASE_ERROR);
            }
        }
        return app;
    }

    public boolean updateMusic(int musicId, int duration, String publisher, String name, float price) throws Exception {
        boolean updated = false;
        if (databaseConnection.isConnected()) {
            try {
                loadContent("Music", musicId);
                this.contents.get(musicId).setPublisher(publisher);
                this.contents.get(musicId).setName(name);
                this.contents.get(musicId).setPrice(price);
                ((Music) this.contents.get(musicId)).setDuration(duration);
                if (this.contents.get(musicId).update(databaseConnection)) {
                    updated = true;
                }

            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return updated;
    }

    //app
    public Content getAppById(int appId) throws Exception {
        Content app = null;
        if (this.contents.containsKey(appId)) {
            app = this.contents.get(appId);
        } else {
            if (databaseConnection.isConnected()) {
                try {
                    Content tmp = new App(appId);
                    if (tmp.load(databaseConnection)) {
                        this.contents.put(tmp.getContentId(), tmp);
                        app = tmp;
                    }
                } catch (Exception e) {
                    throw new Exception(e);
                }
            } else {
                throw new Exception(DATABASE_ERROR);
            }
        }
        return app;
    }

    public boolean updateApp(int appId, String details, String publisher, String name, float price) throws Exception {
        boolean updated = false;
        if (databaseConnection.isConnected()) {
            try {
                loadContent("App", appId);
                this.contents.get(appId).setPublisher(publisher);
                this.contents.get(appId).setName(name);
                this.contents.get(appId).setPrice(price);
                ((App) this.contents.get(appId)).setDetails(details);
                if (this.contents.get(appId).update(databaseConnection)) {
                    updated = true;
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return updated;
    }

    //video
    public Content getVideoById(int videoId) throws Exception {
        Content app = null;
        if (this.contents.containsKey(videoId)) {
            app = this.contents.get(videoId);
        } else {
            if (databaseConnection.isConnected()) {
                try {
                    Content tmp = new Video(videoId);
                    if (tmp.load(databaseConnection)) {
                        this.contents.put(tmp.getContentId(), tmp);
                        app = tmp;
                    }
                } catch (Exception e) {
                    throw new Exception(e);
                }
            } else {
                throw new Exception(DATABASE_ERROR);
            }
        }
        return app;
    }

    public boolean updateVideo(int videoId, String resolution, String publisher, String name, float price) throws Exception {
        boolean updated = false;
        if (databaseConnection.isConnected()) {
            try {
                loadContent("App", videoId);
                this.contents.get(videoId).setPublisher(publisher);
                this.contents.get(videoId).setName(name);
                this.contents.get(videoId).setPrice(price);
                ((Video) this.contents.get(videoId)).setResolution(resolution);
                if (this.contents.get(videoId).update(databaseConnection)) {
                    updated = true;
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return updated;
    }

    //all conntents - cascade delete
    public boolean deleteContent(String contentType, int contentId) throws Exception {
        boolean deleted = false;
        if (databaseConnection.isConnected()) {
            try {
                loadContent(contentType, contentId);
                if (this.contents.get(contentId).delete(databaseConnection)) {
                    this.contents.remove(contentId);
                    deleted = true;
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return deleted;
    }

    //buys
    public Content getContentByUserId(int userId, int contentId) throws Exception {
        Content content = null;
        if (databaseConnection.isConnected()) {
            try {
                loadUser(userId);
                content = this.users.get(userId).getContentById(contentId);
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return content;
    }

    public Collection<Content> getContentsByUserId(int userId) throws Exception {
        Collection<Content> contents = null;
        if (databaseConnection.isConnected()) {
            try {
                loadUser(userId);
                contents = this.users.get(userId).getContents();
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return contents;
    }

    public boolean createBuy(int userId, String contentType, int contentId) throws Exception {
        boolean added = false;
        if (databaseConnection.isConnected()) {
            try {
                loadContent(contentType, contentId);
                loadUser(userId);
                if (this.users.get(userId).buyContent(databaseConnection, this.contents.get(contentId))) {
                    added = true;
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception(DATABASE_ERROR);
        }
        return added;
    }

}
