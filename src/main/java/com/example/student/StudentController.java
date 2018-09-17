package com.example.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "studnets")
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

    @ApiOperation(value = "Returns Student details", notes = "Returns Student details.", response = Student.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Result", response = Student.class) })
    @RequestMapping(value = "students/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id).get();
    }

    @RequestMapping(value = "students/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    @RequestMapping(value = "guides/", method = RequestMethod.GET)
    public List<Guide> getGuides() {
        return guideRepository.findAll();
    }

}
