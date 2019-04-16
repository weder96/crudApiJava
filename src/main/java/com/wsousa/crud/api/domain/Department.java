package com.wsousa.crud.api.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;


@Entity
public class Department {
	@Id
	private int id;
	private String name;
	@OneToMany(mappedBy = "department")
	private Set<Professor> employees = new HashSet<Professor>();

	public int getId() {
		return id;
	}

	public void setId(int deptNo) {
		this.id = deptNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String deptName) {
		this.name = deptName;
	}

	public Set<Professor> getProfessors() {
		return employees;
	}

	public String toString() {
		return "Department no: " + getId() + ", name: " + getName();
	}
}