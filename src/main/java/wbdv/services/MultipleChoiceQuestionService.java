package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Exam;
import wbdv.models.MultipleChoiceExamQuestion;
import wbdv.repositories.ExamRepository;
import wbdv.repositories.MultipleChoicesQuestionRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    MultipleChoicesQuestionRepository multipleChoiceRepository;

    @PostMapping("/api/exam/{eid}/choice")
    public MultipleChoiceExamQuestion createMultipleChoiceQuestion(@PathVariable("eid") int examId,
                                                                   @RequestBody MultipleChoiceExamQuestion newMultipleChoiceQuestion) {
        Optional<Exam> optional = examRepository.findById(examId);
        if (optional.isPresent()) {
            Exam exam = optional.get();
            newMultipleChoiceQuestion.setExam(exam);
            return multipleChoiceRepository.save(newMultipleChoiceQuestion);
        }
        return null;
    }

    @GetMapping("/api/multi/{questionId}")
    public MultipleChoiceExamQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
        Optional<MultipleChoiceExamQuestion> optional = multipleChoiceRepository.findById(questionId);
        return optional.orElse(null);
    }

}
