package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Lesson;
import wbdv.models.Topic;
import wbdv.repositories.LessonRepository;
import wbdv.repositories.TopicRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {


    @Autowired
    TopicRepository topicRepository;


    @Autowired
    LessonRepository lessonRepository;


    @GetMapping("/api/topic/{topicId}")
    public Topic findTopicById(@PathVariable("topicId") int topicId) {
        Optional<Topic> data = topicRepository.findById(topicId);
        return data.orElse(null);
    }

    @GetMapping("/api/topic")
    public List<Topic> findAllTopics() {
        return (List<Topic>) topicRepository.findAll();
    }

    @GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
    public List<Topic> findAllTopicsForLesson(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId, @PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if(data.isPresent()) {
            Lesson lesson = data.get();
            return lesson.getTopics();
        }
        return null;
    }

    @PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
    public Topic createTopic(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId, @PathVariable("lessonId") int lessonId,
                             @RequestBody Topic newTopic) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if (data.isPresent()) {
            Lesson lesson = data.get();
            newTopic.setLesson(lesson);
            return topicRepository.save(newTopic);
        }
        return null;
    }


    @PutMapping("/api/topic/{topicId}")
    public Topic updateTopic(@PathVariable("topicId") int topicId, @RequestBody Topic newTopic) {
        Optional<Topic> data = topicRepository.findById(topicId);
        if (data.isPresent()) {
            Topic topic = data.get();
            topic.setId(newTopic.getId());
            topic.setTitle(newTopic.getTitle());
            topic.setLesson(newTopic.getLesson());
            System.out.println("Module: " + topic);
            topicRepository.save(topic);
            return topic;
        }
        return null;
    }

    @DeleteMapping("/api/topic/{topicId}")
    public void deleteTopic(@PathVariable("topicId") int topicId) {
        topicRepository.deleteById(topicId);
    }
}
