package com.example.lab5sem2.repositories;

import com.example.lab5sem2.entities.DevTeam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DevTeamRepository extends CrudRepository<DevTeam, Integer> {
}
