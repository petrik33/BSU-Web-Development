package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Developer")
public class Developer {
    public Developer (String name, Qualification qualification, int payRate, DevTeam team) {
        this.name = name;
        this.qualification = qualification;
        this.payRate = payRate;
        this.team = team;
        this.currentProject = null;
    }

    public Developer() {

    }

    @Override
    public String toString() {
        String returned = getId().toString() + ") " + getName() + ", " + getQualification().toString() + ", " + getPayRate().toString() + ", " + getTeam().getName() + ", ";
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

    @Column(name = "name", nullable = false)
    protected String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualification", nullable = false)
    protected Qualification qualification;

    @Column(name = "pay")
    protected Integer payRate;

    @ManyToOne
    @JoinColumn(name = "projectId", nullable = false)
    protected Project currentProject;

    @OneToOne
    @JoinColumn(name = "teamId", nullable = false)
    protected DevTeam team;

    // DATA ACCESS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
