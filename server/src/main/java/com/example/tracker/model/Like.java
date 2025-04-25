package com.example.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
@IdClass(LikeId.class)
@EntityListeners(AuditingEntityListener.class)
public class Like {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "track_id")
    private Long trackId;

    @CreatedDate
    @Column(name = "liked_at")
    private LocalDate likedAt;
}
