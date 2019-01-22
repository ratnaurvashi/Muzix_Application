package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        catch (TrackAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks(){
        ResponseEntity responseEntity;
        try {
            responseEntity= new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("track/{trackId}")
    public ResponseEntity<?> removeTrack(@PathVariable int trackId){
        ResponseEntity responseEntity;
        Track track1 = null;
        try {
            track1 = trackService.getTrackById(trackId);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }

        try {
            trackService.removeTrack(track1);
            responseEntity = new ResponseEntity<String>("Track removed", HttpStatus.OK);
        }
        catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @GetMapping("track/{trackId}")
    public ResponseEntity<?> getTrackById(@PathVariable int trackId){
        ResponseEntity responseEntity;
        try {
            responseEntity= new ResponseEntity<Track>(trackService.getTrackById(trackId), HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("track")
    public ResponseEntity<?> updateComment(@RequestBody Track track){
        ResponseEntity responseEntity;

        try {
            trackService.updateComment(track);
            responseEntity= new ResponseEntity<String>("Success Updation",HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("track1/{trackName}")
    public ResponseEntity<?> getTrackByName(@PathVariable String trackName){
        return new ResponseEntity<List<Track>>(trackService.getTrackByName(trackName), HttpStatus.OK);
    }
}
