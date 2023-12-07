package Entities;

import Entities.Qualification;

public class Job {

    public Job(Specification specification, Qualification qualification, int number) {
        this.specification = specification;
        this.requiredDevelopersNumber = number;
        this.requiredQualification = qualification;
    }

    public Specification getSpecification() {
        return specification;
    }

    public Integer getRequiredDevelopersNumber() {
        return requiredDevelopersNumber;
    }

    public Qualification getRequiredQualification() {
        return requiredQualification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void setRequiredDevelopersNumber(Integer requiredDevelopersNumber) {
        this.requiredDevelopersNumber = requiredDevelopersNumber;
    }

    public void setRequiredQualification(Qualification requiredQualification) {
        this.requiredQualification = requiredQualification;
    }

    protected Specification specification;
    protected Integer requiredDevelopersNumber;
    protected Qualification requiredQualification;

    // DATA ACCESS

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
