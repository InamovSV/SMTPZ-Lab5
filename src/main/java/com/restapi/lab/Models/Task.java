package com.restapi.lab.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    String label;
    Date time;
    boolean isReady;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Employee.class)
    @JoinColumn(name = "performer_id")
    private Employee performer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean bool) {
        this.isReady = bool;
    }

    public Task(String label, Date time) {
        this.label = label;
        this.time = time;
        this.isReady = false;
    }

    public Task(String label){
        this.label = label;
        this.isReady = false;
    }

    public Task(int id, String label, Date time) {
        this.id = id;
        this.label = label;
        this.time = time;
        this.isReady = false;
    }

    public Task(){

    }

    public String toString() {
        return "id: " + id + " label: " + label + " who: " + " time: " + time.toString() + " is ready: " + isReady;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(toString());
//        var task = (Task)obj;
//        if(task != null){
//            return id == task.id && label.equals(task.label) && who.equals(task.who) && time == task.time && isReady == task.isReady;
//        } else return false;
    }

    public Employee getPerformer() {
        return performer;
    }

    public void setPerformer(Employee performer) {
        this.performer = performer;
    }
}