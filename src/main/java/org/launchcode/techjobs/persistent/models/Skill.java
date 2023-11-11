package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {
    public Skill() {
    }

    // Add description field
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description is too long (maximum 255 characters)")
    private String description;

    @ManyToMany(mappedBy = "skills",fetch= FetchType.EAGER)
    private List<Job> jobs = new ArrayList<>();


    // Add description accessors
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}