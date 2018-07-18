package wbdv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Course;
import wbdv.models.Module;
import wbdv.repositories.CourseRepository;
import wbdv.repositories.ModuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;


    @GetMapping("/api/module")
    public List<Module> findAllModules() {
        return (List<Module>) moduleRepository.findAll();
    }

    @GetMapping("/api/course/{courseId}/module")
    public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
        Optional<Course> data = courseRepository.findById(courseId);
        if (data.isPresent()) {
            Course course = data.get();
            return course.getModules();
        }
        return null;
    }


    @PostMapping("/api/course/{courseId}/module")
    public Module createModule(@PathVariable("courseId") int courseId, @RequestBody Module module) {
        Optional<Course> data = courseRepository.findById(courseId);
        if (data.isPresent()) {
            Course course = data.get();
            Module m = new Module();
            m.setCourse(course);
            m.setTitle(module.getTitle());
            return moduleRepository.save(m);
        }
        return null;
    }


    @DeleteMapping("/api/module/{mid}")
    public void deleteModule(@PathVariable("mid") int mid) {
        moduleRepository.deleteById(mid);
    }

}
