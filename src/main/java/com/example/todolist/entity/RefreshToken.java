package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

    public RefreshToken(UUID refreshToken, UserEntity user, Date expiryDate) {
        this.user = user;
        this.expiryDate = expiryDate;
        this.refreshToken = refreshToken;
        this.issuedAt = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(unique = true, nullable = false)
    private UUID refreshToken;

    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(unique = true, nullable = true)
    private UUID deviceId;

    @Column(nullable = false)
    private Date expiryDate;

    @Column(nullable = false)
    private Date issuedAt;

}
