package wbdv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Course;
import wbdv.models.Module;
import wbdv.repositories.CourseRepository;
import wbdv.repositories.ModuleRepository;
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

    @GetMapping("/api/module/{moduleId}")
    public Module findModuleById(@PathVariable("moduleId") int moduleId) {
        Optional<Module> data = moduleRepository.findById(moduleId);
        return data.orElse(null);
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

    @PutMapping("/api/module/{moduleId}")
    public Module updateModule(@PathVariable("moduleId") int moduleId, @RequestBody Module newModule) {
        Optional<Module> data = moduleRepository.findById(moduleId);
        if (data.isPresent()) {
            Module module = data.get();
            module.setId(newModule.getId());
            module.setTitle(newModule.getTitle());
            module.setCourse(newModule.getCourse());
            moduleRepository.save(module);
            return module;
        }
        return null;
    }


    @DeleteMapping("/api/module/{mid}")
    public void deleteModule(@PathVariable("mid") int mid) {
        moduleRepository.deleteById(mid);
    }

}
