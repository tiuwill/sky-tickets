package br.com.springcloud.skytickets.order.order.repository.domain;

public class Order {

    private Long id;
    private String buyerName;
    private Long movieId;
    private String sessionTime;
    private String cardNumber;

    public Order(Long id, String buyerName, Long movieId, String sessionTime, String cardNumber) {
        this.id = id;
        this.buyerName = buyerName;
        this.movieId = movieId;
        this.sessionTime = sessionTime;
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
