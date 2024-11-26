package com.cityClass.CityClass.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String user_name;
    @NotEmpty
    //@Email

    private String mail;
    public String getMail() {
        return mail;
    }
    @NotEmpty
    //@Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @NotEmpty
    //@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;
}
