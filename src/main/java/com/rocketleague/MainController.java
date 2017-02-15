package com.rocketleague;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/tracked-players")
public class MainController {

    @Autowired
    private TrackedPlayerRepository trackedPlayerRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<TrackedPlayer> getAllTrackedPlayers() {
        return trackedPlayerRepository.findAll();
    }
}
