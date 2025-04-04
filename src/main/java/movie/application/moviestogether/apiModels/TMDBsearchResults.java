package movie.application.moviestogether.apiModels;


import java.util.List;

public class TMDBsearchResults {

    private int page;

    private List<TMDBsimpleMovie> results;

    private int total_pages;

    private int total_results;


    public TMDBsearchResults() {
    }


    public TMDBsearchResults(int page, List<TMDBsimpleMovie> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }
    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TMDBsimpleMovie> getResults() {
        return this.results;
    }

    public void setResults(List<TMDBsimpleMovie> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return this.total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return this.total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    
}

   