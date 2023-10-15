package Entities;

import java.util.EnumMap;

public class Invoice {
    public Invoice(Project project, Customer customer, Integer amount) {
        this.project = project;
        this.customer = customer;
        this.amount = amount;
        this.isPaid = false;
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
}
