package com.cityClass.CityClass.DTO;

public class ReviewDTO {
    private String username;
    private String comment;

    // Конструктор
    public ReviewDTO(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

    // Геттеры и сеттеры
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
