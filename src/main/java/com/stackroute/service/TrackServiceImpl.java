package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
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
    public Track saveTrack(Track track)throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("User Already Exists");
        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack==null){
            throw new TrackAlreadyExistsException("User Already Exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() throws TrackNotFoundException {
        if(trackRepository==null){
            throw new TrackNotFoundException("Track Not Found");
        }
        return trackRepository.findAll();
    }

    public Track getTrackById(int trackId) throws TrackNotFoundException{
        if(!trackRepository.existsById(trackId)){
            throw new TrackNotFoundException("Track Not Found");
        }
        Track track1 = trackRepository.findById(trackId).get();
        if(track1==null){
            throw new TrackNotFoundException("Track Not Found");
        }
        return track1;
    }

    @Override
    public Track updateComment(Track track) throws TrackNotFoundException{
        if(!trackRepository.existsById(track.getTrackId())){
            throw new TrackNotFoundException("Track Not Found");
        }
        Track updateTrack = trackRepository.save(track);
        if(updateTrack==null){
            throw new TrackNotFoundException("Track Not Found");
        }
        return updateTrack;
    }

    @Override
    public void removeTrack(Track track) throws TrackNotFoundException {
        if(!trackRepository.existsById(track.getTrackId())){
            throw new TrackNotFoundException("Track Not Found");
        }
        trackRepository.deleteById(track.getTrackId());
    }

    @Override
    public List<Track> getTrackByName(String trackName){
        return trackRepository.getByName(trackName);
    }
}
