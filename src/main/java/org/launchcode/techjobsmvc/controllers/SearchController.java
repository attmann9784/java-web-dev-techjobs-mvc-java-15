package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.data.JobData;
import org.launchcode.techjobsmvc.models.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;

@Controller
@RequestMapping("search")
public class SearchController {


    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.toLowerCase().equals("") || searchTerm.toLowerCase().equals(" ")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", searchType + "Jobs with " + searchTerm);
            model.addAttribute("title", columnChoices.get(searchTerm));
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("type", searchType);
        return "search";
    }
}
