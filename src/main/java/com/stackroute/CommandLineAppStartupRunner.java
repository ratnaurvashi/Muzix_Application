package com.stackroute;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@PropertySource("classpath:application.properties")
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    Environment env;

    @Override
    public void run(String...args) throws Exception {
        System.out.println("CommandLineRunner running");
        Track track = new Track(Integer.parseInt(env.getProperty("trackId")),env.getProperty("trackName"),env.getProperty("comments"));
        trackRepository.save(track);
    }
}