package Entities;

import Entities.Qualification;

public class Job {

    public Job(Qualification qualification, int number) {
        this.requiredDevelopersNumber = number;
        this.requiredQualification = qualification;
    }

    public void setRequiredDevelopersNumber(Integer requiredDevelopersNumber) {
        this.requiredDevelopersNumber = requiredDevelopersNumber;
    }

    public void setRequiredQualification(Qualification requiredQualification) {
        this.requiredQualification = requiredQualification;
    }

    public Integer getRequiredDevelopersNumber() {
        return requiredDevelopersNumber;
    }

    public Qualification getRequiredQualification() {
        return requiredQualification;
    }

    protected Integer requiredDevelopersNumber;
    protected Qualification requiredQualification;
}
