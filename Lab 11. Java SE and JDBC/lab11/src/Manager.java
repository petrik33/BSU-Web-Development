import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Manager {

    Manager(String name, int payRate) {
        this.name = name;
        this.payRate = payRate;
        this.projects = new Vector<>();
    }

    public void JoinTeam(DevTeam team) {
        this.team = team;
    }

    public boolean StartProject(Project project) {
        if (team == null) return false;
        projects.add(project);
        return true;
    }

    public Vector<Developer> FindFreeDevelopers(Vector<Job> specification) {
        if (this.team == null) {
            return new Vector<>();
        }

        Vector<Developer> foundDevelopers = new Vector<>();

        for (Job job : specification) {
            Qualification qualification = job.GetQualification();
            int requiredNumber = job.GetNumber();
            List<Developer> developers = FindFreeQualifiedDevelopers(qualification);
            if (developers.size() < requiredNumber) {
                return new Vector<>();
            }
            developers.sort(Comparator.comparingInt(Developer::GetPayRate));
            for (int i = 0; i < requiredNumber; ++i) {
                foundDevelopers.add(developers.get(i));
            }
        }

        return foundDevelopers;
    }

    protected List<Developer> FindFreeQualifiedDevelopers(Qualification qualification) {
        return team
                .GetDevelopers()
                .stream()
                .filter(developer -> (developer.GetQualification() == qualification && developer.GetCurrentProject() == null))
                .collect(Collectors.toList());
    }

    protected String name;
    protected Integer payRate;
    protected DevTeam team;
    protected Vector<Project> projects;
}
