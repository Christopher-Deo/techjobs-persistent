package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Skill extends AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name is too long (maximum 255 characters)")
    private String name;

    // Add description field
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description is too long (maximum 255 characters)")
    private String description;

    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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