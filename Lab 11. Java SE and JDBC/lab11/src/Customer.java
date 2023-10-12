import java.util.Vector;

public class Customer {

    Customer (String name) {
        this.name = name;
        this.jobs = new Vector<>();
    }

    public void AddJob(Job job) {
        jobs.add(job);
    }

    public Vector<Job> GetSpecification() {
        return jobs;
    }

    public String GetName() {
        return name;
    }

    protected Vector<Job> jobs;
    protected String name;
}
