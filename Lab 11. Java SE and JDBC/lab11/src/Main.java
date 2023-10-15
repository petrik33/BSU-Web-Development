import Controllers.ProjectPlan;
import DataPackage.DAOException;
import Entities.*;
import EntitiesDao.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void outputObject(Object object) throws IllegalAccessException {
//        Class<?> aClass = object.getClass();
//        // Получаем все поля объекта
//        Field[] fields = aClass.getDeclaredFields();
//
//        for (Field field : fields) {
//            field.setAccessible(true);
//            String fieldName = field.getName();
//            Object value = field.get(aClass);
//            System.out.println(fieldName + ": " + value);
//        }
        System.out.println(object.toString());
    }

    public static void main(String[] args) throws IllegalAccessException, DAOException {
        // Task 1
        DaoCustomer daoCustomer = new DaoCustomer();
        Optional<Customer> customer1 = daoCustomer.get(1);
        if (!customer1.isPresent()) {
            throw new DAOException("Can't find customer with id: 1");
        }

        DaoProject daoProject = new DaoProject();
        List<Project> projects = daoProject.getProjectsForCustomer(customer1.get());

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

        for (Developer developer : developers) {
            outputObject(developer);
        }

        // Task 3
        List<Developer> developers2 = daoDeveloper.getDevelopersByProject(projects.get(0));
        for (Developer developer : developers2) {
            outputObject(developer);
        }

        // Task 4
        DaoInvoice daoInvoice = new DaoInvoice();
        Optional<Invoice> invoice3 = daoInvoice.get(3);
        if (!invoice3.isPresent()) {
            throw new DAOException("Can't find invoice with id: 1");
        }

        outputObject(invoice3);
        daoInvoice.Pay(invoice3.get());
        outputObject(invoice3.get());
    }
}