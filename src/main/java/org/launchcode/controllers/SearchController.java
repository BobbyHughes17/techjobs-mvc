package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
        }
        // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "/results")
    public String getResults(Model model,@RequestParam String searchType, String searchTerm){

        ArrayList<HashMap<String,String>> jobs = new ArrayList<>();

        model.addAttribute("columns", ListController.columnChoices);
        System.out.println(searchType);
        if(searchTerm.isEmpty()){
            String err = "A search Term is required.";
            model.addAttribute("err",err);
        }
        else if(searchType.equals("all")){
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("jobs",jobs);

        }
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", jobs);
        }
        return "search";
    }
}