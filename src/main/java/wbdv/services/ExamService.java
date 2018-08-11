package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.BaseExamQuestion;
import wbdv.models.Exam;
import wbdv.models.Topic;
import wbdv.repositories.ExamRepository;
import wbdv.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/exam")
    public Iterable<Exam> findAllExams() {
        return examRepository.findAll();
    }

    @GetMapping("/api/exam/{eid}")
    public Exam findExamById(@PathVariable("eid") int examId) {
        Optional<Exam> optional = examRepository.findById(examId);
        return optional.orElse(null);
    }

    @GetMapping("api/topic/{tid}/exam")
    public List<Exam> findExamsByTopicId(@PathVariable("tid") int topicId) {
        Optional<Topic> optional = topicRepository.findById(topicId);
        if (optional.isPresent()) {
            Topic topic = optional.get();
            return topic.getExams();
        }

        return null;
    }

    @PostMapping("/api/topic/{tid}/exam")
    public Exam createExam(@PathVariable("tid") int topicId,
                           @RequestBody Exam newExam) {
        Optional<Topic> optional = topicRepository.findById(topicId);
        if (optional.isPresent()) {
            Topic topic = optional.get();
            newExam.setTopic(topic);
            return examRepository.save(newExam);
        }
        return null;
    }

    @DeleteMapping("/api/exam/{eid}")
    public void deleteExam(@PathVariable("eid") int examId) {
        examRepository.deleteById(examId);
    }

    @GetMapping("/api/exam/{examId}/question")
    public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);
        if(optionalExam.isPresent()) {
            Exam exam = optionalExam.get();
            List<BaseExamQuestion> questions = exam.getQuestions();
            int count = questions.size();
            return questions;
        }
        return null;
    }
}
