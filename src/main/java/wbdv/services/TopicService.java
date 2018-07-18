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

    @GetMapping("/api/lesson/{lessonId}/topic")
    public List<Topic> findAllTopicsForLesson(@PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if (data.isPresent()) {
            Lesson lesson = data.get();
            return lesson.getTopics();
        }
        return new ArrayList<>();
    }

    @PostMapping("/api/lesson/{lessonId}/topic")
    public Topic createTopic(@RequestBody Topic topic, @PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if (data.isPresent()) {
            Lesson lesson = data.get();
            Topic t = new Topic();
            t.setTitle(topic.getTitle());
            t.setLesson(lesson);
            return topicRepository.save(t);
        }
        return null;
    }

    @DeleteMapping("/api/topic/{topicId}")
    public void deleteTopic(@PathVariable("topicId") int topicId) {
        topicRepository.deleteById(topicId);
    }
}
