package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Assignment;
import wbdv.models.Lesson;
import wbdv.models.Topic;
import wbdv.repositories.AssignmentRepository;
import wbdv.repositories.LessonRepository;
import wbdv.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    AssignmentRepository assignmentRepository;

    @GetMapping("/api/assignment")
    public Iterable<Assignment> findAllAssignments() {
        return assignmentRepository.findAll();
    }

    @GetMapping("/api/assignment/{aid}")
    public Assignment findAssignmentById(@PathVariable("aid") int assignmentId) {
        Optional<Assignment> optional = assignmentRepository.findById(assignmentId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @GetMapping("/api/lesson/{lid}/assignment")
    public List<Assignment> findAssignmentsByLessonId(@PathVariable("lid") int lessonId) {
        Optional<Lesson> optional = lessonRepository.findById(lessonId);
        if (optional.isPresent()) {
            Lesson lesson = optional.get();
            return lesson.getAssignments();
        }
        return null;
    }

    @PostMapping("/api/lesson/{lid}/assignment")
    public Assignment createNewAssignment(@PathVariable("lid") int lessonId,
                                          @RequestBody Assignment newAssignment) {
        Optional<Lesson> optional = lessonRepository.findById(lessonId);
        if (optional.isPresent()) {
            Lesson lesson = optional.get();
            newAssignment.setLesson(lesson);
            return newAssignment;
        }
        return null;
    }

    @DeleteMapping("/api/assignment/{aid}")
    public void deleteAssignmentById(@PathVariable("aid") int assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }
}