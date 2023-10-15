package Entities;

import Entities.*;

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

    public DevTeam getTeam() {
        return team;
    }

    public Integer getPayRate() {
        return payRate;
    }

    public String getName() {
        return name;
    }

    public Vector<Project> getProjects() {
        return projects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPayRate(Integer payRate) {
        this.payRate = payRate;
    }

    public void joinTeam(DevTeam team) {
        this.team = team;
    }

    public boolean startProject(Project project) {
        if (team == null) return false;
        projects.add(project);
        return true;
    }

    protected String name;
    protected Integer payRate;
    protected DevTeam team;
    protected Vector<Project> projects;
}
