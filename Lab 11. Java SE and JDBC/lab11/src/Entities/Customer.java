package Entities;

import Entities.Job;

import java.util.Vector;

public class Customer {

    public Customer(String name) {
        this.name = name;
        this.jobs = new Vector<>();
    }

    public Vector<Job> getSpecification() {
        return jobs;
    }

    public String GetName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    protected Vector<Job> jobs;
    protected String name;
}
