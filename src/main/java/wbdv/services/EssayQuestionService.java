package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.EssayExamQuestion;
import wbdv.models.Exam;
import wbdv.repositories.EssayQuestionRepository;
import wbdv.repositories.ExamRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class EssayQuestionService {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    EssayQuestionRepository essayRepository;

    @PostMapping("/api/exam/{eid}/essay")
    public EssayExamQuestion createEssayExamQuestion(@PathVariable("eid") int examId,
                                                     @RequestBody EssayExamQuestion newEssayQuestion) {
        Optional<Exam> optional = examRepository.findById(examId);
        if (optional.isPresent()) {
            Exam exam = optional.get();
            newEssayQuestion.setExam(exam);
            return essayRepository.save(newEssayQuestion);
        }
        return null;
    }

    @GetMapping("/api/essay/{questionId}")
    public EssayExamQuestion findEssayQuestionById(@PathVariable("questionId") int questionId) {
        Optional<EssayExamQuestion> optional = essayRepository.findById(questionId);
        return optional.orElse(null);
    }
}
