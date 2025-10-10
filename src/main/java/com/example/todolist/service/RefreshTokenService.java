package com.example.todolist.service;

import com.example.todolist.entity.RefreshToken;
import com.example.todolist.entity.UserEntity;
import com.example.todolist.exception.DataBaseExecutionException;
import com.example.todolist.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${token.refreshExpiration}")
    private long refreshTokenExpirationInDays;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public UUID createRefreshToken(UUID refreshToken, long userId, UUID deviceId) {
        // Implementation for creating a refresh token
        try {
            if (refreshTokenRepository.findByDeviceId(deviceId).isPresent()) {
                refreshTokenRepository.updateByDeviceId(deviceId, refreshToken, new Date((new Date()).getTime() + refreshTokenExpirationInDays), new Date());
                return refreshToken;
            }

            if(refreshTokenRepository.countByUser(userId)>=5) {
                refreshTokenRepository.deleteByRefreshToken(
                        refreshTokenRepository.findOldestTokenById(userId)
                        .orElseThrow(() -> new DataBaseExecutionException("Service not found Refresh token.")));
            }
            refreshTokenRepository.add(refreshToken, userId, new Date((new Date()).getTime() + refreshTokenExpirationInDays), new Date());
        } catch (DataAccessException ex) {
            throw new DataBaseExecutionException("Failed to create refresh token." + ex.getMessage());
        }
        return refreshToken;
    }


    @Transactional
    public UserEntity findUserByRefreshToken(UUID refreshToken) {
        return refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> new DataBaseExecutionException("Service not found Refresh token."))
                .getUser();
    }

    public RefreshToken findRefreshTokenByRefreshToken(UUID refreshToken) {
        return refreshTokenRepository.findById(refreshToken).orElseThrow(() -> new DataBaseExecutionException("Service not found Refresh token."));
    }

    public boolean isSameDevice(UUID refreshToken, UUID deviceId) {
        RefreshToken rT = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> new DataBaseExecutionException("Service not found Refresh token."));
        return rT.getDeviceId() == deviceId;
    }

    public boolean isExpired(UUID refreshToken) {
        Date expiryDate = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> new DataBaseExecutionException("Service not found Refresh token."))
                .getExpiryDate();
        if(expiryDate.before(new Date())){
            refreshTokenRepository.deleteById(refreshToken);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteByRefreshToken(String refreshToken) {
        try {
            refreshTokenRepository.deleteByRefreshToken(UUID.fromString(refreshToken));
            System.out.println("Deleted refresh token: " + refreshToken);
        } catch (DataAccessException ex) {
            throw new DataBaseExecutionException("Service failed to delete refresh token." + ex.getMessage());
        }
        return true;
    }

    @Transactional
    public boolean deleteById(long userId) {
        try {
            refreshTokenRepository.deleteById(userId);
        } catch (DataAccessException ex) {
            throw new DataBaseExecutionException("Service failed to delete refresh token." + ex.getMessage());
        }
        return true;
    }


}
