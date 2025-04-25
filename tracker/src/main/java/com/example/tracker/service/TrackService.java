package com.example.tracker.service;

import com.example.tracker.model.Track;
import com.example.tracker.repository.TrackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;

    public List<Track> findAllTracks() {
        return trackRepository.findAll();
    }

    public Track findTrackById(Long id) {
        return trackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Track not found"));
    }
}
