package Entities;

import javax.persistence.*;

@Entity
@Table(name = "Developer")
@NamedQueries({
        @NamedQuery(name = "Developer.selectById", query = "SELECT d FROM Developer d WHERE d.id = :id"),
        @NamedQuery(name = "Developer.selectAll", query = "SELECT d FROM Developer d"),
        @NamedQuery(name = "Developer.selectByTeam", query = "SELECT d FROM Developer d WHERE d.team.id = :teamId"),
        @NamedQuery(name = "Developer.selectByProject", query = "SELECT d FROM Developer d WHERE d.currentProject.id = :projectId"),
        @NamedQuery(name = "Developer.assignProject", query = "UPDATE Developer d SET d.currentProject.id = :projectId WHERE d.id = :developerId")
})
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
