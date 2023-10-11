import java.util.EnumMap;

public class Job {

    Job() {
        requiredDevelopers = new EnumMap<>(Qualification.class);
    }

    public int GetRequirement(Qualification qualification) {
        return requiredDevelopers.get(qualification);
    }

    public void RequireDevelopers(Qualification qualification, Integer number) {
        requiredDevelopers.putIfAbsent(qualification, 0);
        requiredDevelopers.put(qualification, requiredDevelopers.get(qualification) + number);
    }

    protected EnumMap<Qualification, Integer> requiredDevelopers;
}
