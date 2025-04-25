package com.example.tracker.controller;

import com.example.tracker.dto.LikeRequest;
import com.example.tracker.model.CustomUserDetails;
import com.example.tracker.model.Like;
import com.example.tracker.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping
    public ResponseEntity<List<Like>> index() {
        List<Like> likes = likeService.getAllLikes();
        return ResponseEntity.ok(likes);
    }

    @PostMapping
    public ResponseEntity<Void> add(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody LikeRequest request
    ) {
        likeService.addLike(userDetails.getId(), request.getTrackId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLikeById(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id
    ) {
        likeService.deleteByUserAndTrack(userDetails.getId(), id);
        return ResponseEntity.noContent().build();
    }
}
