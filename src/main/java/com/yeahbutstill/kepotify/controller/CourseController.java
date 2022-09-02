package com.yeahbutstill.kepotify.controller;

import com.yeahbutstill.kepotify.entity.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

    // http://localhost:8080/api/v1/courses
    @GetMapping("/courses")
    public List<Course> getAllCourses() {

        return Collections.singletonList(Course.builder()
                .name("Learn evrythings AXZ")
                .author("Dani")
                .build()
        );

    }

}
