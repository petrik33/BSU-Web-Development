import java.util.Vector;

public class DevTeam {

    DevTeam (String name) {
        this.name = name;
        this.developers = new Vector<>();
    }

    public void AddDeveloper(Developer developer) {
        this.developers.add(developer);
    }

    public Vector<Developer> getDevelopers() {
        return developers;
    }

    protected String name;
    protected Vector<Developer> developers;
}
