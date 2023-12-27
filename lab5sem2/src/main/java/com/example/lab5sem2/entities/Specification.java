package com.example.lab5sem2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "Specification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    protected Customer customer;
}
