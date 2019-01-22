package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks() throws TrackNotFoundException;

    public Track getTrackById(int trackId) throws TrackNotFoundException;

    public Track updateComment(Track track) throws TrackNotFoundException;

    public void removeTrack(Track track) throws TrackNotFoundException;

    public List<Track> getTrackByName(String trackName);
}
