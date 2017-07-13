package com.example.student;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    private Guide guide;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

}
