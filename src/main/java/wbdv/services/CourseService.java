package wbdv.services;

import org.springframework.web.bind.annotation.*;
import wbdv.models.Course;
import wbdv.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/course")
    public List<Course> findAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @PostMapping("/api/course")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/api/course/{courseId}")
    public void deleteCourse(@PathVariable("courseId") int id){
        courseRepository.deleteById(id);
    }


}
