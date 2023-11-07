import Controllers.LogicException;
import Controllers.TeamController;
import DataPackage.DAOException;

import java.sql.SQLException;

import static Logger.JLogManager.logException;

public class Main {

    public static void main(String[] args) {
        TeamController controller = new TeamController();

        // Task 1
        try {
            controller.OutputCustomerProjects(1);
        } catch (DAOException | SQLException | LogicException e) {
            logException(e);
            return;
        }

        // Task 2
        try {
            controller.OutputProjectDevelopers(2);
        } catch (DAOException | SQLException | LogicException e) {
            logException(e);
            return;
        }

        // Task 3
        try {
            controller.OutputTeamDevelopers(1);
        } catch (DAOException | SQLException | LogicException e) {
            logException(e);
            return;
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
            return;
        }
    }
}