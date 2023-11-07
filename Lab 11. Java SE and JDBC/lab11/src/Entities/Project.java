package Entities;

import jakarta.persistence.*;


@Entity
@Table(name = "Project")
@NamedQueries({
        @NamedQuery(name = "Project.selectById", query = "SELECT c FROM Project c WHERE c.id = :id"),
        @NamedQuery(name = "Project.selectAll", query = "SELECT c FROM Project c"),
        @NamedQuery(name = "Project.byCustomerStatement", query = "SELECT p FROM Project p JOIN Invoice i ON p.id = i.project.id WHERE i.customer.id = :customerId")
})
public class Project {

    public Project(String name, Specification specification) {
        this.name = name;
        this.specification = specification;
    }

    public Project() {

    }

    @Override
    public String toString() {
        return getId().toString() + ") " + getName() + " by " + getTeam().getName();
    }

    public String getName() {
        return name;
    }

    public Specification getSpecification() {
        return specification;
    }

    public DevTeam getTeam() {
        return team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void setTeam(DevTeam team) {
        this.team = team;
    }

    @Column(name = "name", nullable = false)
    protected String name;

    @OneToOne
    @JoinColumn(name = "specificationId")
    protected Specification specification;

    @OneToOne
    @JoinColumn(name = "teamId")
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
