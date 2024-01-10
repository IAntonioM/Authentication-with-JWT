package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.persistence.entity.Course;
import com.example.springsecurityjwt.persistence.repository.CourseRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @PreAuthorize("hasAuthority('READ_ALL_Course')")
    @GetMapping
    public ResponseEntity<List<Course>> findAll(){
        List<Course> courses=courseRepository.findAll();
        if(courses!=null && !courses.isEmpty()){
            return ResponseEntity.ok(courses);
        }
        return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasAuthority('SAVE_ONE_Course')")
    @PostMapping
    public ResponseEntity<Course> createOne(@RequestBody @Valid Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                courseRepository.save(course)
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGenericException(Exception exception, HttpServletRequest request){
        Map<String, String> apiError=new HashMap<>();
        apiError.put("message",exception.getLocalizedMessage());
        apiError.put("timestamp",new Date().toString());
        apiError.put("url",request.getRequestURL().toString());
        apiError.put("http-method",request.getMethod());

        HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;

        if(exception instanceof AccessDeniedException){
            status=HttpStatus.FORBIDDEN;
        }
        return ResponseEntity.status(status).body(apiError);
    }
}
