public class Job {

    Job(Qualification qualification, int number) {
        this.requiredDevelopersNumber = number;
        this.requiredQualification = qualification;
    }

    public int GetNumber() {
        return requiredDevelopersNumber;
    }

    public Qualification GetQualification() {
        return requiredQualification;
    }

    protected Integer requiredDevelopersNumber;
    protected Qualification requiredQualification;
}
