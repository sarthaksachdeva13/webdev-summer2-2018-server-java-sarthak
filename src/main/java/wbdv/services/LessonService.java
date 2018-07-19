package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Lesson;
import wbdv.models.Module;
import wbdv.repositories.LessonRepository;
import wbdv.repositories.ModuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
    public List<Lesson> findAllLessonsForModule(
            @PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId) {
        Optional<Module> data = moduleRepository.findById(moduleId);
        if(data.isPresent()) {
            Module module = data.get();
            return module.getLessons();
        }
        return null;
    }

    @PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
    public Lesson createLesson(@PathVariable("courseId") int courseId,
                               @PathVariable("moduleId") int moduleId,
                               @RequestBody Lesson newLesson) {
        Optional<Module> data = moduleRepository.findById(moduleId);
        if (data.isPresent()) {
            Module module = data.get();
            newLesson.setModule(module);
            return lessonRepository.save(newLesson);
        }
        return null;
    }


    @DeleteMapping("/api/lesson/{lessonId}")
    public void deleteLesson(@PathVariable("lessonId") int lessonId)
    {
        lessonRepository.deleteById(lessonId);
    }

    @GetMapping("/api/lesson")
    public List<Lesson> findAllLessons()
    {
        return (List<Lesson>) lessonRepository.findAll();
    }

    @GetMapping("/api/lesson/{lessonId}")
    public Lesson findLessonById(@PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        return data.orElse(null);
    }

    @PutMapping("/api/lesson/{lessonId}")
    public Lesson updateLesson(@PathVariable("lessonId") int lessonId, @RequestBody Lesson newLesson) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if (data.isPresent()) {
            Lesson lesson = data.get();
            lesson.setId(newLesson.getId());
            lesson.setTitle(newLesson.getTitle());
            lesson.setModule(newLesson.getModule());
            System.out.println(lesson);
            lessonRepository.save(lesson);
            return lesson;
        }
        return null;
    }
}