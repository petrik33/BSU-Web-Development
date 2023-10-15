package Entities;

public class Project {

    public Project(String name, int hoursLong) {
        this.name = name;
        this.hoursLeft = hoursLong;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void makeProgress(int hours) {
        hoursLeft -= hours;
    }

    public boolean isActive() {
        return hoursLeft > 0;
    }

    protected String name;
    protected Integer hoursLeft;
}
