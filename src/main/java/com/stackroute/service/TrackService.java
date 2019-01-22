package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track);

    public List<Track> getAllTracks();

    public Track getTrackById(int trackId);

    public Track updateComment(Track track);

    public void removeTrack(Track track);

    public List<Track> getTrackByName(String trackName);
}
