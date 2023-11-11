package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 100, message = "Location is too long (maximum 100 characters)")
    private String location;

    @OneToMany(fetch= FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return location; // Adjust as needed for your application
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}