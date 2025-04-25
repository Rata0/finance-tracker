package com.example.tracker.controller;

import com.example.tracker.model.Track;
import com.example.tracker.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tracks")
public class TrackController {
    @Autowired
    private TrackService trackService;

    @GetMapping
    public ResponseEntity<List<Track>> getAllTracks() {
        List<Track> trackList = trackService.findAllTracks();
        return ResponseEntity.ok(trackList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> show(@PathVariable Long id) {
        Track track = trackService.findTrackById(id);
        return ResponseEntity.ok(track);
    }
}
