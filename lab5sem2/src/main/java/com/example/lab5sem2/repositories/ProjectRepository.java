package com.example.lab5sem2.repositories;

import com.example.lab5sem2.entities.DevTeam;
import com.example.lab5sem2.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
