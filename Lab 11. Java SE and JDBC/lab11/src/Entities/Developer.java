package Entities;

import java.util.HashMap;

public class Developer {
    public Developer (String name, Qualification qualification, int payRate) {
        this.name = name;
        this.qualification = qualification;
        this.payRate = payRate;
        this.currentProject = null;
        this.workingLog = new HashMap<>();
    }

    public Integer getPayRate() {
        return payRate;
    }

    public String getName() {
        return name;
    }

    public DevTeam getTeam() {
        return team;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public Integer getHoursWorked(Project project) {
        if (project == null) return 0;
        return workingLog.get(project.getName());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPayRate(Integer payRate) {
        this.payRate = payRate;
    }

    public void setCurrentProject(Project project) {
        this.currentProject = project;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public void setTeam(DevTeam team) {
        this.team = team;
    }

    public void work(int hours) {
        logWork(currentProject, hours);
        currentProject.makeProgress(hours);
    }

    protected void logWork(Project project, int hours) {
        if (project == null) {
            return;
        }

        String key = project.getName();
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
    protected DevTeam team;
}
