package com.example.tracker.repository;

import com.example.tracker.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserIdAndTrackId(Long userId, Long trackId);
    void deleteByUserIdAndTrackId(Long userId, Long trackId);
}
