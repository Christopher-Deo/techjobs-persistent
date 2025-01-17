package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {
//        model.addAttribute("jobs", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll()); // Assuming you have findAll method in employerRepository
        model.addAttribute("skills", skillRepository.findAll()); // Assuming you have findAll method in skillRepository
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }

        // Fetch the selected employer
        newJob.setEmployer(employerRepository.findById(employerId).orElse(null));

        // Fetch skills from the repository using skillRepository
//        Iterable<Skill> selectedSkillsIterable = skillRepository.findAllById(skills);

        // Convert the iterable to a list
        List<Skill> selectedSkills = (List<Skill>)skillRepository.findAllById(skills);
//        selectedSkillsIterable.forEach(selectedSkills::add);

        // Set the skills for the new job
        newJob.setSkills(selectedSkills);

        // Save the job to the job repository
        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> optJob = jobRepository.findById(jobId); // Find employer by ID

        if (optJob.isPresent()) {
            Job job = optJob.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:/";
        }

    }
}