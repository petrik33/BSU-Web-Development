import java.util.HashMap;

public class Developer {

    Developer (String name, Qualification qualification, int payRate) {
        this.name = name;
        this.qualification = qualification;
        this.payRate = payRate;
        this.currentProject = null;
        this.workingLog = new HashMap<>();
    }

    public Project GetCurrentProject() {
        return currentProject;
    }

    public Qualification GetQualification() {
        return qualification;
    }

    public int GetPayRate() {
        return payRate;
    }

    public void AssignProject(Project project) {
        currentProject = project;
    }

    public void Work(int hours) {
        LogWork(currentProject, hours);
        currentProject.Progress(hours);
    }

    protected void LogWork(Project project, int hours) {
        String key = project.GetName();
        if (!workingLog.containsKey(key)) {
            workingLog.put(key, hours);
            return;
        }
        workingLog.put(key, workingLog.get(key) + hours);
    }

    protected String name;
    protected Qualification qualification;
    protected Integer payRate;
    protected Project currentProject;
    protected HashMap<String, Integer> workingLog;
}
