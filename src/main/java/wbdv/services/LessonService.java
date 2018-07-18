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


    @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(@PathVariable("lid") int lid) {
        Optional<Lesson> data = lessonRepository.findById(lid);
        return data.orElse(null);
    }

    @GetMapping("/api/lesson")
    public List<Lesson> findAllLessons() {
        return (List<Lesson>) lessonRepository.findAll();
    }

    @GetMapping("/api/course/{cid}/module/{mid}/lesson")
    public List<Lesson> findAllLessonsForModule(@PathVariable("cid") int cid, @PathVariable("mid") int mid) {
        Optional<Module> moduleData = moduleRepository.findById(mid);
        if (moduleData.isPresent()) {
            Module module = moduleData.get();
            return module.getLessons();
        }
        return null;
    }

    @PostMapping("/api/course/{cid}/module/{mid}/lesson")
    public Lesson createLesson(@RequestBody Lesson newLesson, @PathVariable("cid") int cid,
                               @PathVariable("mid") int mid) {
        Optional<Module> data = moduleRepository.findById(mid);
        if (data.isPresent()) {
            Module module = data.get();
            Lesson lesson = new Lesson();
            lesson.setTitle(newLesson.getTitle());
            lesson.setModule(module);
            return lessonRepository.save(lesson);
        }
        else {
            return null;
        }
    }


    @PutMapping("/api/lesson/{lessonId}")
    public Lesson updateLesson(@PathVariable("lessonId") int lessonId, @RequestBody Lesson newLesson) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if (data.isPresent()) {
            Lesson lesson = data.get();
            lesson.setId(newLesson.getId());
            lesson.setTitle(newLesson.getTitle());
            lesson.setModule(newLesson.getModule());
            lessonRepository.save(lesson);
            return lesson;
        }
        return null;
    }

    @DeleteMapping("/api/lesson/{id}")
    public void deleteLesson(@PathVariable("id") int id) {
        lessonRepository.deleteById(id);
    }

}
