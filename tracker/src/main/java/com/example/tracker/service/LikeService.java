package com.example.tracker.service;

import com.example.tracker.model.Like;
import com.example.tracker.model.Track;
import com.example.tracker.repository.LikeRepository;
import com.example.tracker.repository.TrackRepository;
import com.example.tracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrackRepository trackRepository;

    public void addLike(Long userId, Long trackId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        trackRepository.findById(trackId)
                .orElseThrow(() -> new EntityNotFoundException("Track not found"));

        if (likeRepository.existsByUserIdAndTrackId(userId, trackId)) {
            throw new EntityNotFoundException("Track already liked by this user");
        }

        Like like = new Like();
        like.setUserId(userId);
        like.setTrackId(trackId);
        likeRepository.save(like);
    }

    public void deleteByUserAndTrack(Long userId, Long trackId) {
        if (!likeRepository.existsByUserIdAndTrackId(userId, trackId)) {
            throw new EntityNotFoundException("Like not found");
        }
        likeRepository.deleteByUserIdAndTrackId(userId, trackId);
    }

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }
}
