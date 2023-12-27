package com.example.lab5sem2.controllers;

import com.example.lab5sem2.repositories.DeveloperRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/developers")
public class DeveloperController {
    DeveloperRepository developerRepository;

    public DeveloperController(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @GetMapping("/all")
    public String getAllDevelopers(Model model) {
        model.addAttribute("developers", developerRepository.findAll());
        return "developer/list";
    }

    @GetMapping("/byTeam")
    public String getAllDevelopersByTeamId(@RequestParam("teamId") int teamId, Model model) {
        model.addAttribute("developers", developerRepository.findAllByTeam(teamId));
        return "developer/list";
    }

    @GetMapping("/byProject")
    public String getAllDevelopersByProjectId(@RequestParam("projectId") int projId, Model model) {
        model.addAttribute("developers", developerRepository.findAllByCurrentProject(projId));
        return "developer/list";
    }
}
