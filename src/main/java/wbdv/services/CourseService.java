//package wbdv.services;
//
//import wbdv.models.Course;
//import wbdv.repositories.CourseRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@RestController
//public class CourseService {
//
//    @Autowired
//    CourseRepository courseRepository;
//
//    @GetMapping("api/course")
//    public List<Course> findAllCourses() {
//        return (List<Course>) courseRepository.findAll();
//    }
//
//
//
//}
