package wbdv.services;

import org.springframework.web.bind.annotation.*;
import wbdv.models.Course;
import wbdv.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/course")
    public Iterable<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/api/course")
    public Course createCourse
            (@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/api/course/{courseId}")
    public void deleteCourse(
            @PathVariable("courseId") int id) {
        courseRepository.deleteById(id);
    }

    @GetMapping("/api/course/{courseId}")
    public Course findCourseById(@PathVariable("courseId") int courseId) {
        Optional<Course> data = courseRepository.findById(courseId);
        return data.orElse(null);
    }

    @PutMapping("/api/course/{courseId}")
    public Course updateCourse(@PathVariable("courseId") int courseId, @RequestBody Course newCourse) {
        Optional<Course> data = courseRepository.findById(courseId);
        if (data.isPresent()) {
            Course course = data.get();
            course.setId(newCourse.getId());
            course.setTitle(newCourse.getTitle());
            course.setCreated(newCourse.getCreated());
            course.setModified(newCourse.getModified());
            course.setModules(newCourse.getModules());
            System.out.println(course);
            courseRepository.save(course);
            return course;
        }
        return null;
    }

}