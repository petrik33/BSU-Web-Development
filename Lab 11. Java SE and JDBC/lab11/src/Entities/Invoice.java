package Entities;

import java.util.EnumMap;

public class Invoice {
    public Invoice(Project project, Customer customer, Integer amount) {
        this.project = project;
        this.customer = customer;
        this.amount = amount;
        this.isPaid = false;
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

    protected Project project;
    protected boolean isPaid;
    protected Customer customer;
    protected Integer amount;

    // DATA ACCESS

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
