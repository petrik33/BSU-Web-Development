// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Customer man = new Customer(new String("Me"));
        man.AddJob(new Job(Qualification.INTERN, 1));
        man.AddJob(new Job(Qualification.SENIOR, 1));

        Developer d1 = new Developer(new String("J1"), Qualification.INTERN, 10);
        Developer d2 = new Developer(new String("J2"), Qualification.SENIOR, 30);
        Developer d3 = new Developer(new String("J3"), Qualification.SENIOR, 50);

        Manager sasha = new Manager(new String("Alexandr"), 33);

        DevTeam teamTest = new DevTeam(new String("BSU"));

        sasha.JoinTeam(teamTest);
        teamTest.AddDeveloper(d1);
        teamTest.AddDeveloper(d2);
        teamTest.AddDeveloper(d3);

        ProjectPlan plan = ProjectPlan
                .Init(man, new String("New Project"), 500)
                .Assign(sasha);

        Invoice invoice = plan.MakeInvoice();
        Project project = plan.Start();

        int total = invoice.GetTotal();
    }
}