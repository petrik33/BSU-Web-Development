package com.example.lab5sem2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "Developer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Developer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Column(name = "name", nullable = false)
  protected String name;

  @Column(name = "pay")
  protected Integer payRate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id")
  protected Project currentProject;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  protected DevTeam team;
}
