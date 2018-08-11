package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Assignment;
import wbdv.models.Topic;
import wbdv.repositories.AssignmentRepository;
import wbdv.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

    @Autowired
    TopicRepository topicRepository;
    @Autowired
    AssignmentRepository assignmentRepository;

    @GetMapping("/api/assignment")
    public Iterable<Assignment> findAllAssignments() {
        return assignmentRepository.findAll();
    }

    @GetMapping("/api/assignment/{aid}")
    public Assignment findAssignmentById(@PathVariable("aid") int assignmentId) {
        Optional<Assignment> optional = assignmentRepository.findById(assignmentId);
        return optional.orElse(null);
    }

    @GetMapping("/api/topic/{lid}/assignment")
    public List<Assignment> findAssignmentsByTopicId(@PathVariable("lid") int topicId) {
        Optional<Topic> optional = topicRepository.findById(topicId);
        if (optional.isPresent()) {
            Topic topic = optional.get();
            return topic.getAssignments();
        }
        return null;
    }

    @PostMapping("/api/topic/{lid}/assignment")
    public Assignment createNewAssignment(@PathVariable("lid") int topicId, @RequestBody Assignment newAssignment) {
        Optional<Topic> optional = topicRepository.findById(topicId);
        if (optional.isPresent()) {
            Topic topic = optional.get();
            newAssignment.setTopic(topic);
            return newAssignment;
        }
        return null;
    }

    @DeleteMapping("/api/assignment/{aid}")
    public void deleteAssignmentById(@PathVariable("aid") int assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }
}