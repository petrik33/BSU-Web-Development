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
        } catch (SQLException e) {
            throw new DAOException("Error initializing prepared statements: " + e.getMessage());
        }
    }

    @Override
    public Optional<Job> convertFullSet(ResultSet resultSet) throws SQLException, DAOException {
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
}
