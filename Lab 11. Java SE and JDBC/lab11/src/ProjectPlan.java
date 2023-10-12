import java.util.Vector;

public class ProjectPlan {

    public static ProjectPlan Init(Customer customer, String name, int hoursLong) {
        ProjectPlan plan = new ProjectPlan();
        plan.customer = customer;
        plan.developers = new Vector<>();
        plan.project = new Project(name, hoursLong);
        return plan;
    }

    public ProjectPlan Assign(Manager manager) {
        this.manager = manager;
        this.developers = this.manager.FindFreeDevelopers(customer.GetSpecification());
        return this;
    }

    public boolean IsPossible() {
        return this.manager != null && this.developers != null && !this.developers.isEmpty();
    }

    public Invoice MakeInvoice() {
        Invoice invoice = new Invoice(project, customer);
        for (Developer dev : developers) {
            invoice.IncreaseAmount(dev.qualification, dev.payRate);
        }
        invoice.SetManagementPay(manager.payRate);
        return invoice;
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
    protected Customer customer;
    protected Manager manager;
    protected Vector<Developer> developers;
}
