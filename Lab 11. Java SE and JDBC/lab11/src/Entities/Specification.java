package Entities;

import java.util.Vector;
import jakarta.persistence.*;

@Entity
@Table(name = "Specification")
@NamedQueries({
        @NamedQuery(name = "Specification.selectById", query = "SELECT c FROM Specification c WHERE c.id = :id"),
        @NamedQuery(name = "Specification.selectAll", query = "SELECT c FROM Specification c")
})
public class Specification {
    public Specification(Customer customer) {
        this.customer = customer;
        this.jobs = new Vector<>();
    }

    public Specification() {

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

    @Transient
    protected Vector<Job> jobs;

    @OneToOne
    @JoinColumn(name = "customerId", nullable = false)
    protected Customer customer;

    // DATA ACCESS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
