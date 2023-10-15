package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.DevTeam;
import Entities.Job;
import Entities.Qualification;
import Entities.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoJob extends DaoMySql<Job> {
    private DaoSpecification daoSpecification;

    public DaoJob() throws DAOException {
        super();
        this.daoSpecification = new DaoSpecification();
        try {
            getStatement = connector.getConnection().prepareStatement("SELECT * FROM Job WHERE id = ?");
            getAllStatement = connector.getConnection().prepareStatement("SELECT * FROM Job");
            saveStatement = connector.getConnection().prepareStatement("INSERT INTO Job (specificationID, qualification, required) VALUES (?, ?, ?)");
            updateStatement = connector.getConnection().prepareStatement("UPDATE Job SET specificationID = ?, qualification = ?, required = ? WHERE id = ?");
            deleteStatement = connector.getConnection().prepareStatement("DELETE FROM Job WHERE id = ?");
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<Job> get(long id) {
        try {
            getStatement.setLong(1, id);
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                int jobId = resultSet.getInt("id");
                int specificationID = resultSet.getInt("specificationID");
                String qualification = resultSet.getString("qualification");
                int required = resultSet.getInt("required");
                Optional<Specification> specification = daoSpecification.get(specificationID);
                if (!specification.isPresent()) {
                    return Optional.empty();
                }
                Job job = new Job(specification.get(), Qualification.valueOf(qualification), required);
                job.setId(jobId);
                return Optional.of(job);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return Optional.empty();
    }

    @Override
    public List<Job> getAll() {
        List<Job> jobs = new ArrayList<>();
        try {
            ResultSet resultSet = getAllStatement.executeQuery();
            while (resultSet.next()) {
                int jobId = resultSet.getInt("id");
                int specificationID = resultSet.getInt("specificationID");
                String qualification = resultSet.getString("qualification");
                int required = resultSet.getInt("required");
                Optional<Specification> specification = daoSpecification.get(specificationID);
                if (!specification.isPresent()) {
                    continue;
                }
                Job job = new Job(specification.get(), Qualification.valueOf(qualification), required);
                job.setId(jobId);
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return jobs;
    }

    @Override
    public void save(Job job) {
        try {
            saveStatement.setInt(1, job.getSpecification().getId());
            saveStatement.setString(2, job.getRequiredQualification().toString());
            saveStatement.setInt(3, job.getRequiredDevelopersNumber());
            saveStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void update(Job job) {
        try {
            updateStatement.setInt(1, job.getSpecification().getId());
            updateStatement.setString(2, job.getRequiredQualification().toString());
            updateStatement.setInt(3, job.getRequiredDevelopersNumber());
            updateStatement.setInt(4, job.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }

    @Override
    public void delete(Job job) {
        try {
            deleteStatement.setInt(1, job.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
    }
}
