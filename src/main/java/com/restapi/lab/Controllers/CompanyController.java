package com.restapi.lab.Controllers;

import com.restapi.lab.DAO.CompanyRep;
import com.restapi.lab.Models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(path="companies")
public class CompanyController {
    @Autowired
    private CompanyRep companyRep;

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {

        model.addAttribute("companies", companyRep.findAll());
        return "companiesList";
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id) {

        ModelAndView model = new ModelAndView("companyDetail");

        model.addObject("company", companyRep.findById(id).get());
        return model;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {
        companyRep.deleteById(id);
        return new ModelAndView("redirect:/companies");
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Company company) {
        companyRep.save(company);
        return new ModelAndView("redirect:/companies");
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute Company company) {
        company.setId(id);
        companyRep.save(company);
        return new ModelAndView("redirect:/companies");
    }

}
