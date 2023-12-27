package com.example.lab5sem2.repositories;

import com.example.lab5sem2.entities.Developer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeveloperRepository extends CrudRepository<Developer, Integer> {
  @Query("select dev from Developer dev where dev.currentProject.id = :id")
  List<Developer> findAllByCurrentProject(@Param("id") int id);

  @Query("select dev from Developer dev where dev.team.id = :id")
  List<Developer> findAllByTeam(@Param("id") int id);

  @Query("select dev from Developer dev")
  List<Developer> findAllDevelopers();

  @Query(value = "delete from Developer dev where dev.id = :id")
  @Modifying
  void deleteById(@Param("id") int id);
}
