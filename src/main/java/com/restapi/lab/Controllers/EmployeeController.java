package com.restapi.lab.Controllers;

import com.restapi.lab.DAO.CompanyRep;
import com.restapi.lab.DAO.EmployeeRep;
import com.restapi.lab.Models.Requests.CreateEmployee;
import com.restapi.lab.Models.Employee;
import com.restapi.lab.Models.Requests.UpdateEmployee;
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
@RequestMapping(path = "employees")
public class EmployeeController {
    @Autowired
    private EmployeeRep employeeRep;

    @Autowired
    private CompanyRep companyRep;

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {

        model.addAttribute("employees", employeeRep.findAll());
        return "employeesList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id) {

        ModelAndView model = new ModelAndView("employeesDetail");

        model.addObject("employee", employeeRep.findById(id).get());
        return model;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {
        employeeRep.deleteById(id);
        return new ModelAndView("redirect:/employees");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute CreateEmployee employee) {

        Employee res = new Employee(employee.getFullname(), employee.getPosition());
        res.setCompany(companyRep.findById(employee.getCompany_id()).orElse(null));
        employeeRep.save(res);

        return new ModelAndView("redirect:/employees");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Integer id, @ModelAttribute UpdateEmployee employee) {
        Employee res = employeeRep.findById(id).get();
        res.setFullname(employee.getFullname());
        res.setPosition(employee.getPosition());
        res.setCompany(companyRep.findById(employee.getCompany_id()).orElse(null));
        employeeRep.save(res);
        return new ModelAndView("redirect:/employees");
    }
}