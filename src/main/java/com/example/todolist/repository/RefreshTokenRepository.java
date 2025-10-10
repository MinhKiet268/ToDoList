package com.example.todolist.repository;

import com.example.todolist.entity.RefreshToken;
import com.example.todolist.entity.UserEntity;
import org.hibernate.Remove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {


    void deleteByRefreshToken(UUID refreshToken);

    Optional<RefreshToken> findByDeviceId(UUID refreshToken);

    @Modifying
    @Query (value = "UPDATE refresh_token SET refresh_token = :refreshToken, issued_at = :issuedAt, expiry_date = :expiryDate WHERE device_id = :deviceId", nativeQuery = true)
    void updateByDeviceId(@Param("deviceId") UUID deviceId, @Param("refreshToken") UUID refreshToken, @Param("expiryDate") Date expiryDate, @Param("issuedAt") Date issuedAt);

    @Modifying
    @Query(value = "DELETE FROM refresh_token WHERE user_id = :id", nativeQuery = true)
    void deleteById(long id);

    @Modifying
    @Query(value = "SELECT rt FROM refresh_token rt WHERE user_id = :id ORDER BY rt.expiry_data LIMIT 1", nativeQuery = true)
    Optional<UUID> findOldestTokenById(long id);

    @Modifying
    @Query(
            value = "INSERT INTO refresh_token (refresh_token, user_id, expiry_date, issued_at) " +
                    "VALUES (:token, :userId, :expiryDate, :issuedAt)",
            nativeQuery = true // Use standard SQL
    )
    void add(
            @Param("token") UUID token,
            @Param("userId") long userId,
            @Param("expiryDate") Date expiryDate,
            @Param("issuedAt") Date issuedAt
    );

    @Query(
            value = "SELECT COUNT(*) FROM refresh_token WHERE user_id = :userId",
            nativeQuery = true
    )
    public int countByUser(@Param("userId") long userId);
}
