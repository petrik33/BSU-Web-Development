package EntitiesDao;

import DataPackage.DAOException;
import DataPackage.DaoMySql;
import Entities.DevTeam;
import Entities.Developer;
import Entities.Project;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

import static Jdbc.HibernateConnectionPool.getSession;
import static Jdbc.HibernateConnectionPool.releaseSession;

public class DaoDeveloper extends DaoMySql<Developer> {

    @Override
    protected String getByIdNamedQuery() {
        return "Developer.selectById";
    }

    @Override
    protected String getAllNamedQuery() {
        return "Developer.selectAll";
    }

    public List<Developer> getDevelopersByTeam(DevTeam team) throws DAOException {
        List<Developer> developers;
        var session = getSession();
        try {
            String byTeamQuery = "Developer.selectByTeam";
            Query query = session.getNamedQuery(byTeamQuery);
            query.setParameter("teamId", team.getId());
            developers = query.list();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseSession(session);
        }
        return developers;
    }

    public List<Developer> getDevelopersByProject(Project project) throws DAOException {
        List<Developer> developers;
        var session = getSession();
        try {
            String byProjectQuery = "Developer.selectByProject";
            Query query = session.getNamedQuery(byProjectQuery);
            query.setParameter("projectId", project.getId());
            developers = query.list();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            releaseSession(session);
        }
        return developers;
    }

    public void assignProjectToDeveloper(Developer developer, Project project) throws DAOException {
        var session = getSession();
        var tx = session.beginTransaction();
        try {
            String assignProjectQuery = "Developer.assignProject";
            Query query = session.getNamedQuery(assignProjectQuery);
            query.setParameter("projectId", project.getId());
            query.setParameter("developerId", developer.getId());
            query.executeUpdate();

            tx.commit();
            developer.setCurrentProject(project);
        } catch (Exception e) {
            if (Objects.nonNull(tx)) tx.rollback();
            throw new DAOException(e.getMessage());
        } finally {
            releaseSession(session);
        }
    }
}
