package com.example.springsecurityjwt.persistence.repository;

import com.example.springsecurityjwt.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
