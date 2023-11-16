package Entities;

import jakarta.persistence.*;
import java.util.Vector;

@Entity
@Table(name = "Customer")
public class Customer {

    public Customer(String name) {
        this.name = name;
        this.specifications = new Vector<>();
    }

    public Customer() {

    }

    public Vector<Specification> getSpecifications() {
        return specifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSpecification(Specification specification) {
        specifications.add(specification);
    }

    @Transient
    protected Vector<Specification> specifications;

    @Column(name = "name", nullable = false)
    protected String name;

    // DATA ACCESS

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
