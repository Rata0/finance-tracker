package com.example.tracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDTO {
    private Long id;
    private String title;
    private int durationSec;
    private String audioUrl;
    private int playsCount;
    private String artistName;
}
