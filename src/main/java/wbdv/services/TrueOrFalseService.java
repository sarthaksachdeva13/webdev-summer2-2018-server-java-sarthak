package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Exam;
import wbdv.models.TrueOrFalseExamQuestion;
import wbdv.repositories.ExamRepository;
import wbdv.repositories.TrueFalseQuestionRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class TrueOrFalseService {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    TrueFalseQuestionRepository trueFalseRepository;


    @PostMapping("/api/exam/{eid}/trueFalse")
    public TrueOrFalseExamQuestion createTrueFalseQuestion(@PathVariable("eid") int examId,
                                                           @RequestBody TrueOrFalseExamQuestion newTrueFalseQuestion) {
        Optional<Exam> optional = examRepository.findById(examId);
        if (optional.isPresent()) {
            Exam exam = optional.get();
            newTrueFalseQuestion.setExam(exam);
            return trueFalseRepository.save(newTrueFalseQuestion);
        }
        return null;
    }


    @GetMapping("/api/trueFalse/{questionId}")
    public TrueOrFalseExamQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
        Optional<TrueOrFalseExamQuestion> optional = trueFalseRepository.findById(questionId);
        return optional.orElse(null);
    }
}
