package com.example.lab5sem2.controllers;

import com.example.lab5sem2.data.DeveloperData;
import com.example.lab5sem2.entities.Developer;
import com.example.lab5sem2.repositories.DeveloperRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/devs")
@CrossOrigin
public class DeveloperRestController {
  DeveloperRepository developerRepository;

  public DeveloperRestController(DeveloperRepository developerRepository) {
    this.developerRepository = developerRepository;
  }

  @GetMapping
  public List<DeveloperData> getAllDevs() {
    return developerRepository.findAllDevelopers().stream()
      .map(developer -> DeveloperData.builder()
        .id(String.valueOf(developer.getId()))
        .name(developer.getName())
        .build())
      .toList();
  }

  @GetMapping("/{id}")
  public DeveloperData getDevById(@PathVariable int id) {
    return developerRepository.findById(id).stream()
      .map(developer -> DeveloperData.builder()
        .id(String.valueOf(developer.getId()))
        .name(developer.getName())
        .salary(developer.getPayRate())
        .build())
      .findFirst().orElse(null);
  }

  @PostMapping
  public void createDeveloper(@RequestBody DeveloperData developer) {
    developerRepository.save(Developer.builder()
      .name(developer.getName())
      .payRate(developer.getSalary())
      .build());
  }

  @PutMapping("/{id}")
  public void putDevById(@RequestBody DeveloperData developer, @PathVariable int id) {
    Developer entity = developerRepository.findById(id).orElse(null);
    if (Objects.nonNull(entity)) {
      entity.setName(developer.getName());
      entity.setPayRate(developer.getSalary());
      developerRepository.save(entity);
    }
  }

  @DeleteMapping("/{id}")
  public void deleteDevById(@PathVariable int id) {
    developerRepository.deleteById(id);
  }
}
