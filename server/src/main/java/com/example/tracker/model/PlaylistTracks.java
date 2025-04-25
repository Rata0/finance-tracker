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
@Table(name = "playlist_tracks")
@IdClass(PlaylistTrackId.class)
public class PlaylistTracks {
    @Id
    @Column(name = "playlist_id")
    private Long playlistId;

    @Id
    @Column(name = "track_id")
    private Long trackId;

    @Column(name = "added_at")
    private LocalDateTime addedAt;
}
