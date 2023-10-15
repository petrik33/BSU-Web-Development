package Controllers;

import Entities.*;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class TeamController {

    public TeamController(DevTeam devTeam) {
        this.devTeam = devTeam;
    }

    public DevTeam getDevTeam() {
        return devTeam;
    }

    public void setDevTeam(DevTeam devTeam) {
        this.devTeam = devTeam;
    }

    public Vector<Developer> findDevelopers(Vector<Job> specification) {
        if (this.devTeam == null) {
            return new Vector<>();
        }

        Vector<Developer> foundDevelopers = new Vector<>();

        for (Job job : specification) {
            Qualification qualification = job.getRequiredQualification();
            int requiredNumber = job.getRequiredDevelopersNumber();
            List<Developer> developers = findFreeQualifiedDevelopers(qualification);
            if (developers.size() < requiredNumber) {
                return new Vector<>();
            }
            developers.sort(Comparator.comparingInt(Developer::getPayRate));
            for (int i = 0; i < requiredNumber; ++i) {
                foundDevelopers.add(developers.get(i));
            }
        }

        return foundDevelopers;
    }

    protected List<Developer> findFreeQualifiedDevelopers(Qualification qualification) {
        if (this.devTeam == null) return null;
        return devTeam
                .getDevelopers()
                .stream()
                .filter(developer -> (developer.getQualification() == qualification && developer.getCurrentProject() == null))
                .collect(Collectors.toList());
    }

    protected DevTeam devTeam;
}
