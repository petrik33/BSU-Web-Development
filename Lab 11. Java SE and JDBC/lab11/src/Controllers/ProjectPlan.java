package Controllers;

import Entities.*;

import java.util.Vector;

public class ProjectPlan {

    public static ProjectPlan init(Customer customer, String name, int hoursLong) {
        ProjectPlan plan = new ProjectPlan();
        plan.customer = customer;
        plan.developers = new Vector<>();
        plan.project = new Project(name, hoursLong);
        return plan;
    }

    public ProjectPlan assign(DevTeam devTeam) {
        this.devTeam = devTeam;
        this.controller = new TeamController(devTeam);
        this.developers = this.controller.findDevelopers(customer.getSpecification());
        return this;
    }

    public boolean isPossible() {
        return this.devTeam != null && this.developers != null && !this.developers.isEmpty();
    }

    public Invoice makeInvoice() {
        int total = 0;
        for (Developer dev : developers) {
            total += dev.getPayRate();
        }
        total += devTeam.getManager().getPayRate();

        return new Invoice(project, customer, total);
    }

    public Project start() {
        if (!isPossible()) return null;
        for (Developer dev : developers) {
            dev.setCurrentProject(project);
        }
        if (!devTeam.getManager().startProject(project)) {
            return null;
        }
        return project;
    }

    protected Project project;
    protected TeamController controller;
    protected Customer customer;
    protected DevTeam devTeam;
    protected Vector<Developer> developers;
}
