import Controllers.LogicException;
import Controllers.TeamController;
import DataPackage.DAOException;
import Jdbc.JdbcConnectionException;
import Jdbc.JdbcConnector;

import java.sql.SQLException;

import static Jdbc.JdbcConnector.closeFactory;
import static Jdbc.JdbcConnector.initJdbcConnector;
import static Logger.JLogManager.logException;

public class Main {

    public static void main(String[] args) {
        try {
            initJdbcConnector();
        } catch (JdbcConnectionException | ClassNotFoundException e) {
            logException(e);
            closeFactory();
            return;
        }

        TeamController controller = new TeamController();

        // Task 1
        try {
            controller.OutputCustomerProjects(1);
        } catch (DAOException | SQLException | LogicException e) {
            logException(e);
        }

        // Task 2
        try {
            controller.OutputProjectDevelopers(2);
        } catch (DAOException | SQLException | LogicException e) {
            logException(e);
        }

        // Task 3
        try {
            controller.OutputTeamDevelopers(1);
        } catch (DAOException | SQLException | LogicException e) {
            logException(e);
        }

        // Task 4, Part 1
        try {
            controller.AssignProjectToDeveloper(19, 2, 3);
        } catch (DAOException | SQLException | LogicException e) {
            logException(e);
        }

        // Task 4, Part 2
        try {
            controller.PayInvoice(3);
        } catch (DAOException | SQLException | LogicException e) {
            logException(e);
        }

        closeFactory();
    }
}