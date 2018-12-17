package com.restapi.lab.Controllers;

import com.restapi.lab.DAO.EmployeeRep;
import com.restapi.lab.DAO.TaskRep;
import com.restapi.lab.Models.Requests.CreateTask;
import com.restapi.lab.Models.Requests.UpdateTask;
import com.restapi.lab.Models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path="tasks")
public class TaskController {
    @Autowired
    private TaskRep taskRep;

    @Autowired
    private EmployeeRep employeeRep;

    @RequestMapping(method = RequestMethod.GET)
    public String all(Model model) {

        model.addAttribute("tasks", taskRep.findAll());
        return "tasksList";
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id) {

        ModelAndView model = new ModelAndView("taskDetail");

        model.addObject("task", taskRep.findById(id).get());
        return model;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {
        taskRep.deleteById(id);
        return new ModelAndView("redirect:/tasks");
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView create(@ModelAttribute CreateTask task) {
        Task res = new Task(task.getLabel(), task.getTime());
        res.setPerformer(employeeRep.findById(task.getPerformer_id()).orElse(null));
        taskRep.save(res);
        return new ModelAndView("redirect:/tasks");
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute UpdateTask task) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Date time;
        Task res = new Task(task.getLabel());
        try{
            time = format.parse(task.getTime());
            res.setTime(time);
        } catch (ParseException ex){
            System.out.println(ex.getMessage());
            return new ModelAndView("redirect:/tasks");
        }
        res.setPerformer(employeeRep.findById(task.getPerformer_id()).orElse(null));
        res.setId(id);
        taskRep.save(res);
        return new ModelAndView("redirect:/tasks");
    }

    @RequestMapping(value="/done/{id}", method=RequestMethod.POST)
    public ModelAndView done(@PathVariable("id") Integer id) {
        Task task = taskRep.findById(id).orElse(null);
        task.setIsReady(true);
        taskRep.save(task);
        return new ModelAndView("redirect:/tasks");
    }
}
