package Entities;

import java.util.HashMap;

public class Developer {
    public Developer (String name, Qualification qualification, int payRate, DevTeam team) {
        this.name = name;
        this.qualification = qualification;
        this.payRate = payRate;
        this.team = team;
        this.currentProject = null;
    }

    @Override
    public String toString() {
        String returned = getId().toString() + ": " + getName() + ", " + getQualification().toString() + ", " + getPayRate().toString() + ", " + getTeam().getName() + ", ";
        if (getCurrentProject() != null) {
            returned += getCurrentProject().getName();
        } else {
            returned += "no current project";
        }
        return returned;
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

    protected String name;
    protected Qualification qualification;
    protected Integer payRate;
    protected Project currentProject;
    protected DevTeam team;

    // DATA ACCESS

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
