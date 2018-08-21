package wbdv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import wbdv.models.Exam;
import wbdv.models.Lesson;
import wbdv.models.BaseExamQuestion;
import wbdv.repositories.ExamRepository;
import wbdv.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/api/exam")
    public Iterable<Exam> findAllExams() {
        return examRepository.findAll();
    }

    @GetMapping("/api/exam/{eid}")
    public Exam findExamById(@PathVariable("eid") int examId) {
        Optional<Exam> optional = examRepository.findById(examId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @GetMapping("api/lesson/{lid}/exam")
    public List<Exam> findExamsByLessonId(@PathVariable("lid") int lessonId) {
        Optional<Lesson> optional = lessonRepository.findById(lessonId);
        if (optional.isPresent()) {
            Lesson lesson = optional.get();
            return lesson.getExams();
        }

        return null;
    }

    @PostMapping("/api/lesson/{lid}/exam")
    public Exam createExam(@PathVariable("lid") int lessonId,
                           @RequestBody Exam newExam) {
        Optional<Lesson> optional = lessonRepository.findById(lessonId);
        if (optional.isPresent()) {
            Lesson lesson = optional.get();
            newExam.setLesson(lesson);
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