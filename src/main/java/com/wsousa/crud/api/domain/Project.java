package com.wsousa.crud.api.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;

@Entity
@Inheritance
public class Project {
    @Id
    protected int id;
    protected String name;
    @ManyToMany
    protected Collection<Professor> employees = new ArrayList<Professor>();
    
    public int getId() {
        return id;
    }
    
    public void setId(int projectNo) {
        this.id = projectNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String projectName) {
        this.name = projectName;
    }
    
    public Collection<Professor> getProfessors() {
        return employees;
    }
    
    public void addProfessor(Professor employee) {
        if (!getProfessors().contains(employee)) {
            getProfessors().add(employee);
        }
        if (!employee.getProjects().contains(this)) {
            employee.getProjects().add(this);
        }
    }
    
    public String toString() {
        return getClass().getName().substring(getClass().getName().lastIndexOf('.')+1) + 
                " no: " + getId() + 
                ", name: " + getName();
    }
}