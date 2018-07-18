package wbdv.services;

import org.springframework.web.bind.annotation.*;
import wbdv.models.Course;
import wbdv.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/course")
    public List<Course> findAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @GetMapping("api/course/sorted")
    public List<Course> findAllCoursesSorted() {
        return courseRepository.findAllOrderedCourses();
    }

    @PostMapping("/api/course")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/api/course/{courseId}")
    public void deleteCourse(@PathVariable("courseId") int id){
        courseRepository.deleteById(id);
    }

    @GetMapping("api/course/{courseId}")
    public Course findCourseById(@PathVariable("courseId") int courseId) {
        Optional<Course> c = courseRepository.findById(courseId);
        return c.orElse(null);
    }




}
