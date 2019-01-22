package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {
    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService){
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @DeleteMapping("track/{trackId}")
    public ResponseEntity<?> removeTrack(@PathVariable int trackId){
        ResponseEntity responseEntity;
        Track track1 = trackService.getTrackById(trackId);
        trackService.removeTrack(track1);
        responseEntity = new ResponseEntity<String>("Track removed", HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("track/{trackId}")
    public ResponseEntity<?> getTrackById(@PathVariable int trackId){
        Track track = trackService.getTrackById(trackId);
        return new ResponseEntity<Track>(track, HttpStatus.OK);
    }

    @PutMapping("track")
    public ResponseEntity<?> updateComment(@RequestBody Track track){
        return new ResponseEntity<Track>(trackService.updateComment(track),HttpStatus.OK);
    }
}
