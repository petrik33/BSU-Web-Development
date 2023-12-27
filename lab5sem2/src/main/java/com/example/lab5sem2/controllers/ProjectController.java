package com.example.lab5sem2.controllers;

import com.example.lab5sem2.repositories.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/projects")
@RequestMapping("/projects")
public class ProjectController {
    ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/all")
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "project/list";
    }
}
