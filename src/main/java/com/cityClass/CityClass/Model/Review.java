package com.cityClass.CityClass.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)  // Добавляем аннотацию для связи
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    private String review;
    private String name;
}
