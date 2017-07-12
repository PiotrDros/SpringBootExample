package com.example.student;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Set;

@Entity
public class Guide {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "guide")
    @JsonIgnore
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
