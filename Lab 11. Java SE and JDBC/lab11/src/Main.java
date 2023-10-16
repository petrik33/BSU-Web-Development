import DataPackage.DAOException;
import Entities.*;
import EntitiesDao.*;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void outputObject(Object object) {
        System.out.println(object.toString());
    }

    public static void main(String[] args) throws DAOException {
        // Task 1
        DaoCustomer daoCustomer = new DaoCustomer();
        Optional<Customer> customer1 = daoCustomer.get(1);
        if (!customer1.isPresent()) {
            throw new DAOException("Can't find customer with id: 1");
        }

        DaoProject daoProject = new DaoProject();
        List<Project> projects = daoProject.getProjectsForCustomer(customer1.get());

        System.out.println("Projects of " + customer1.get().getName());
        for (Project project : projects) {
            outputObject(project);
        }

        // Task 2
        DaoTeam daoTeam = new DaoTeam();
        Optional<DevTeam> team1 = daoTeam.get(1);
        if (!team1.isPresent()) {
            throw new DAOException("Can't find team with id: 1");
        }

        DaoDeveloper daoDeveloper = new DaoDeveloper();
        List<Developer> developers = daoDeveloper.getDevelopersByTeam(team1.get());

        System.out.println("Developers of " + team1.get().getName());
        for (Developer developer : developers) {
            outputObject(developer);
        }

        // Task 3
        Optional<Project> project2 = daoProject.get(1);
        if (!project2.isPresent()) {
            throw new DAOException("Can't find project with id: 1");
        }

        List<Developer> developers2 = daoDeveloper.getDevelopersByProject(project2.get());

        System.out.println("Developers working on " + project2.get().getName());
        for (Developer developer : developers2) {
            outputObject(developer);
        }

        // Task 4, Part 1
        Optional<Project> project3 = daoProject.get(3);
        if (!project3.isPresent()) {
            throw new DAOException("Can't find project with id: 2");
        }

        Developer testDeveloper;
        if (!developers2.isEmpty()) {
            testDeveloper = developers2.get(0);
        } else {
            testDeveloper = daoDeveloper.getAll().get(0);
        }

        System.out.println("Test developer before assigning project: " + testDeveloper);
        daoDeveloper.assignProjectToDeveloper(testDeveloper, project3.get());
        System.out.println("Test developer after assigning project: " + testDeveloper);

        // Task 4, Part 2
        DaoInvoice daoInvoice = new DaoInvoice();
        Optional<Invoice> invoice3 = daoInvoice.get(3);
        if (!invoice3.isPresent()) {
            throw new DAOException("Can't find invoice with id: 1");
        }

        System.out.println("Invoice before paying: " + invoice3.get());
        daoInvoice.pay(invoice3.get());
        System.out.println("Invoice after paying: " + invoice3.get());
    }
}