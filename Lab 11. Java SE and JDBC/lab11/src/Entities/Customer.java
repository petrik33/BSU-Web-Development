package Entities;

import Entities.Job;

import java.util.Vector;

public class Customer {

    public Customer(String name) {
        this.name = name;
        this.specifications = new Vector<>();
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

    protected Vector<Specification> specifications;
    protected String name;

    // DATA ACCESS

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
