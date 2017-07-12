package com.example.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GuideRepository guideRepository;

    @RequestMapping(value = "students/", method = RequestMethod.POST)
    public void addStudent(@RequestBody Student sutdent) {

        Guide guide;
        List<Guide> guides = guideRepository.findAll();
        if (guides.isEmpty()) {
            guide = new Guide();
            guide.setName("Uber Guide");
        } else {
            guide = guides.get(0);
        }

        sutdent.setGuide(guide);
        studentRepository.save(sutdent);

    }

    @RequestMapping(value = "students/", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @RequestMapping(value = "students/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findOne(id);
    }

    @RequestMapping(value = "students/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.delete(id);
    }

    @RequestMapping(value = "guides/", method = RequestMethod.GET)
    public List<Guide> getGuides() {
        return guideRepository.findAll();
    }

}
