package movie.application.moviestogether.model;

public class EventBase {

    private String date;
    private int listId;
    private int rank;


    public EventBase() {
    }



    public EventBase(String date, int listId, int itemId) {
        this.date = date;
        this.listId = listId;
        this.rank = itemId;
    }


    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getListId() {
        return this.listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int itemId) {
        this.rank = itemId;
    }


    @Override
    public String toString() {
        return "{" +
            " date='" + getDate() + "'" +
            ", listId='" + getListId() + "'" +
            ", rank='" + getRank() + "'" +
            "}";
    }


}
