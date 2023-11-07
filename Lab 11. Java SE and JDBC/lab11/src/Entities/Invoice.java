package Entities;

import java.util.EnumMap;
import jakarta.persistence.*;

@Entity
@Table(name = "Invoices")
@NamedQueries({
        @NamedQuery(name = "Invoice.selectById", query = "SELECT d FROM Invoice d WHERE d.id = :id"),
        @NamedQuery(name = "Invoice.selectAll", query = "SELECT d FROM Invoice d"),
        @NamedQuery(name = "Invoice.payQuery", query = "UPDATE Invoice i SET i.isPaid = :isPaid WHERE id = :id")
})
public class Invoice {
    public Invoice(Project project, Customer customer, Integer amount) {
        this.project = project;
        this.customer = customer;
        this.amount = amount;
        this.isPaid = false;
    }

    public Invoice() {

    }

    @Override
    public String toString() {
        String str = getId().toString() + ") " + amount.toString() + " for " + project.getName() + " issued to " + customer.getName();
        if (isPaid) {
            str += ", Paid";
        } else {
            str += ", To be Paid";
        }
        return str;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public Project getProject() {
        return project;
    }

    @OneToOne
    @JoinColumn(name = "projectId")
    protected Project project;

    @Column(name = "isPaid")
    protected boolean isPaid;

    @OneToOne
    @JoinColumn(name = "customerId")
    protected Customer customer;

    @Column(name = "amount")
    protected Integer amount;

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
