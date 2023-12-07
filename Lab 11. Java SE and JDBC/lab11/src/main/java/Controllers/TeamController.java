package Controllers;

import DataPackage.DAOException;
import Entities.*;
import EntitiesDao.*;
import Jdbc.JdbcConnectionException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TeamController {

    public static void outputObject(Object object) {
        System.out.println(object.toString());
    }

    public TeamController() {}

    public void OutputCustomerProjects(int customerId) throws DAOException, SQLException, LogicException {
        DaoCustomer daoCustomer = new DaoCustomer();
        DaoProject daoProject = new DaoProject();

        Optional<Customer> customer = daoCustomer.get(customerId);
        if (!customer.isPresent()) throw new LogicException("Customer not present");

        List<Project> projects = daoProject.getProjectsForCustomer(customer.get());

        System.out.println("Projects of " + customer.get().getName());
        for (Project project : projects) {
            outputObject(project);
        }
    }

    public void OutputTeamDevelopers(int teamId) throws DAOException, SQLException, LogicException {
        DaoTeam daoTeam = new DaoTeam();
        DaoDeveloper daoDeveloper = new DaoDeveloper();

        Optional<DevTeam> team = daoTeam.get(teamId);
        if (!team.isPresent()) throw new LogicException("Team not present");

        List<Developer> developers = daoDeveloper.getDevelopersByTeam(team.get());

        System.out.println("Developers of " + team.get().getName());
        for (Developer developer : developers) {
            outputObject(developer);
        }
    }

    public void OutputProjectDevelopers(int projectId) throws DAOException, SQLException, LogicException {
        DaoProject daoProject = new DaoProject();
        DaoDeveloper daoDeveloper = new DaoDeveloper();

        Optional<Project> project = daoProject.get(projectId);
        if (!project.isPresent()) throw new LogicException("Project not present");

        List<Developer> developers = daoDeveloper.getDevelopersByProject(project.get());

        System.out.println("Developers working on " + project.get().getName());
        for (Developer developer : developers) {
            outputObject(developer);
        }
    }

    public void AssignProjectToDeveloper(int developerId, int projectId, int testProjectId) throws DAOException, LogicException, SQLException {
        DaoProject daoProject = new DaoProject();
        DaoDeveloper daoDeveloper = new DaoDeveloper();

        Optional<Project> project = daoProject.get(projectId);
        if (!project.isPresent()) throw new LogicException("Project not present");
        Optional<Project> testProject = daoProject.get(testProjectId);
        if (!testProject.isPresent()) throw new LogicException("Test project not present");
        Optional<Developer> developer = daoDeveloper.get(developerId);
        if (!developer.isPresent()) throw new LogicException("Developer not present");

        daoDeveloper.assignProjectToDeveloper(developer.get(), testProject.get());
        System.out.println("Test developer before assigning project: " + developer.get());
        daoDeveloper.assignProjectToDeveloper(developer.get(), project.get());
        System.out.println("Test developer after assigning project: " + developer.get());
    }

    public void PayInvoice(int invoiceId) throws DAOException, LogicException, SQLException {
        DaoInvoice daoInvoice = new DaoInvoice();

        Optional<Invoice> invoice = daoInvoice.get(invoiceId);
        if (!invoice.isPresent()) throw new LogicException("Invoice not present");

        daoInvoice.cancelPayment(invoice.get());
        System.out.println("Invoice before paying: " + invoice.get());

        daoInvoice.pay(invoice.get());
        System.out.println("Invoice after paying: " + invoice.get());
    }
}
