package Entities;

public class Project {

    public Project(String name, Specification specification) {
        this.name = name;
        this.specification = specification;
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

    protected String name;
    protected Specification specification;
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
