package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.*;

@Entity
public class Job {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public Job() {
    }

    public Job(String name, Employer employer, Skill skill) {
        this.name = name;
        this.employer = employer;
        this.skill = skill;
    }

    // Getters and setters for name, employer, and skill
}