public class Project {

    Project(String name, int hoursLong) {
        this.name = name;
        this.hoursLeft = hoursLong;
    }

    public String GetName() {
        return name;
    }

    public void Progress(int hours) {
        hoursLeft -= hours;
    }

    public boolean IsFinished() {
        return hoursLeft <= 0;
    }

    protected String name;
    protected Integer hoursLeft;
}
