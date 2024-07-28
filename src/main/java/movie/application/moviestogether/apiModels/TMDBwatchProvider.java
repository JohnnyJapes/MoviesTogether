package movie.application.moviestogether.apiModels;

public class TMDBwatchProvider {

    private String logo_path, provider_name, type, link;
    private int provider_id, display_priority;

    public TMDBwatchProvider() {
    }



    public TMDBwatchProvider(String logo_path, String provider_name, String type, String link, int provider_id, int display_priority) {
        this.logo_path = logo_path;
        this.provider_name = provider_name;
        this.type = type;
        this.link = link;
        this.provider_id = provider_id;
        this.display_priority = display_priority;
    }




    public String getLogo_path() {
        return this.logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getProvider_name() {
        return this.provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public int getProvider_id() {
        return this.provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public int getDisplay_priority() {
        return this.display_priority;
    }

    public void setDisplay_priority(int display_priority) {
        this.display_priority = display_priority;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}