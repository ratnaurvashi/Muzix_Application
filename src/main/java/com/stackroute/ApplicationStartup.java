package com.stackroute;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:application.properties")
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Value("1")
    private int trackId;

    @Value("default")
    private String trackName;

    @Value("default comments")
    private String comments;

    @Autowired
    TrackRepository trackRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Welcome to Spring Boot Application");
        Track track = new Track(trackId,trackName,comments);
        trackRepository.save(track);
    }
}
