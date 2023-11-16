package Entities;

import java.util.Vector;
import jakarta.persistence.*;

@Entity
@Table(name = "Team")
public class DevTeam {

    public DevTeam(String name) {
        this.name = name;
        this.developers = new Vector<>();
    }

    public DevTeam() {

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

    @Column(name = "name", nullable = false)
    protected String name;

    @Transient
    protected Vector<Developer> developers;

    @Transient
    protected Manager manager;

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
