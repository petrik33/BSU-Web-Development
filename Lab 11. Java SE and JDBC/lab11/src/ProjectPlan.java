import java.util.Vector;

public class ProjectPlan {

    public static ProjectPlan Init(String name, int hoursLong, Vector<Job> specification) {
        ProjectPlan plan = new ProjectPlan();
        plan.specification = specification;
        plan.developers = new Vector<>();
        plan.project = new Project(name, hoursLong);
        return plan;
    }

    public ProjectPlan Assign(Manager manager) {
        this.manager = manager;
        this.developers = this.manager.FindFreeDevelopers(specification);
        return this;
    }

    public boolean IsPossible() {
        return this.manager != null && this.developers != null && !this.developers.isEmpty();
    }

    public Bill EstimateCost() {
        Bill bill = new Bill();
        for (Developer dev : developers) {
            bill.IncreaseAmount(dev.qualification, dev.payRate);
        }
        bill.SetManagementPay(manager.payRate);
        project.bill = bill;
        return bill;
    }

    public Project Start() {
        if (!IsPossible()) return null;
        for (Developer dev : developers) {
            dev.AssignProject(project);
        }
        if (!manager.StartProject(project)) {
            return null;
        }
        return project;
    }

    protected Project project;
    protected Vector<Job> specification;
    protected Manager manager;
    protected Vector<Developer> developers;
}
