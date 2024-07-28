package movie.application.moviestogether.model;

import java.util.List;

public class InviteUsersRequest {


        List<String> newUsers;
        int eventId;
        

    public InviteUsersRequest() {
    }


    public InviteUsersRequest(List<String> newUsers, int eventId) {
        this.newUsers = newUsers;
        this.eventId = eventId;
    }
    

    public List<String> getNewUsers() {
        return this.newUsers;
    }

    public void setNewUsers(List<String> newUsers) {
        this.newUsers = newUsers;
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
        

}
