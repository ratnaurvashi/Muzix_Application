package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track){
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Track getTrackById(int trackId) {
        Track track1 = trackRepository.findById(trackId).get();
        return track1;
    }

    @Override
    public Track updateComment(Track track) {
        Track updateTrack = trackRepository.save(track);
        return updateTrack;
    }

    @Override
    public void removeTrack(Track track) {
        trackRepository.deleteById(track.getTrackId());
    }
}
