package com.cp.kku.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Employee")
public class Employee2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String first_name;
    private String last_name;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public LocalDate getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}

	public String getExpertises() {
		return expertises;
	}

	public void setExpertises(String expertises) {
		this.expertises = expertises;
	}

	private String sex;
    private LocalDate birth_date;
    private String expertises;

    // Default constructor
    public Employee2() {}

    // Constructor with parameters
    public Employee2(int id, String firstName, String lastName, String sex, LocalDate birthDate, String expertises) {
        super();
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.sex = sex;
        this.birth_date = birthDate;
        this.expertises = expertises;
    }

}
