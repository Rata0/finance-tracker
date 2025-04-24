package com.example.tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
@IdClass(LikeId.class)
public class Like {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "track_id")
    private Long trackId;

    @Column(name = "liked_at")
    private LocalDateTime likedAt;
}
