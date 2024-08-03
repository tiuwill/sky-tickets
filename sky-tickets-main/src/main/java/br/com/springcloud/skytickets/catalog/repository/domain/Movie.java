package br.com.springcloud.skytickets.catalog.repository.domain;

public class Movie {

    private Long id;
    private String title;
    private String imageUrl;
    private String sessionTime;

    public Movie(Long id, String title, String imageUrl, String sessionTime) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.sessionTime = sessionTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }
}
