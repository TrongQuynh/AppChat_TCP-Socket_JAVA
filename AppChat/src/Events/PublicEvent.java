package Events;

public class PublicEvent {

    private static PublicEvent instance;
    private EventMain eventMain;
    private EventImageView eventImageView;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventMenuLeft eventMenuLeft;
    private EventMenuRight eventMenuRight;
    private EventCreateGroupChat eventCreateGroupChat;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

    public void addEventMain(EventMain event) {
        this.eventMain = event;
    }

    public void addEventImageView(EventImageView event) {
        this.eventImageView = event;
    }

    public void addEventChat(EventChat event) {
        this.eventChat = event;
    }

    public void addEventLogin(EventLogin event) {
        this.eventLogin = event;
    }

    public EventMain getEventMain() {
        return eventMain;
    }

    public EventImageView getEventImageView() {
        return eventImageView;
    }

    public EventChat getEventChat() {
        return eventChat;
    }

    public EventLogin getEventLogin() {
        return eventLogin;
    }

    public void addEventMenuLeft(EventMenuLeft event) {
        this.eventMenuLeft = event;
    }

    public EventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }
    
    public void addEventMenuRight(EventMenuRight event) {
        this.eventMenuRight = event;
    }

    public EventMenuRight getEventMenuRight() {
        return this.eventMenuRight;
    }

    public EventCreateGroupChat getEventCreateGroupChat() {
        return eventCreateGroupChat;
    }

    public void addEventCreateGroupChat(EventCreateGroupChat eventCreateGroupChat) {
        this.eventCreateGroupChat = eventCreateGroupChat;
    }
    
    
}
