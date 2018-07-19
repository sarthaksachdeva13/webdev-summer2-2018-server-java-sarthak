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


    @GetMapping("api/course/{courseId}")
    public Course findCourseById(@PathVariable("courseId") int courseId) {
        Optional<Course> c = courseRepository.findById(courseId);
        return c.orElse(null);
    }

    @GetMapping("/api/course")
    public List<Course> findAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @GetMapping("api/course/sorted")
    public List<Course> sortedCourses() {
        return courseRepository.sortedCourses();
    }


    @PostMapping("/api/course")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/api/course/{courseId}")
    public void deleteCourse(@PathVariable("courseId") int id){
        courseRepository.deleteById(id);
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
            courseRepository.save(course);
            return course;
        }
        return null;
    }






}
