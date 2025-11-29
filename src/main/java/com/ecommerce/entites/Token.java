package com.ecommerce.entites;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue
    private long id;
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokeType;

    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}

