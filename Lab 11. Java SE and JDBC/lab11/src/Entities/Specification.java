package Entities;

import java.util.Vector;

public class Specification {
    public Specification(Customer customer) {
        this.customer = customer;
        this.jobs = new Vector<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vector<Job> getJobs() {
        return jobs;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    protected Vector<Job> jobs;
    protected Customer customer;

    // DATA ACCESS

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
