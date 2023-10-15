package Entities;

import Entities.Developer;

import java.util.Vector;

public class DevTeam {

    public DevTeam(String name) {
        this.name = name;
        this.developers = new Vector<>();
    }

    public String getName() {
        return name;
    }

    public Vector<Developer> getDevelopers() {
        return developers;
    }

    public Manager getManager() {
        return manager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void addDeveloper(Developer developer) {
        this.developers.add(developer);
    }

    protected String name;
    protected Vector<Developer> developers;
    protected Manager manager;
}
