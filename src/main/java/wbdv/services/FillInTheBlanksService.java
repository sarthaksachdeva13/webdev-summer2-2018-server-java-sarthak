package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Exam;
import wbdv.models.FillInTheBlanksExamQuestion;
import wbdv.repositories.ExamRepository;
import wbdv.repositories.FillInTheBlanksQuestionRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlanksService {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    FillInTheBlanksQuestionRepository fillInBlanksRepository;

    @PostMapping("/api/exam/{eid}/blanks")
    public FillInTheBlanksExamQuestion createFillInBlanksQuestion(@PathVariable("eid") int examId,
                                                                  @RequestBody FillInTheBlanksExamQuestion newFillInBlanksQuestion) {
        Optional<Exam> optional = examRepository.findById(examId);
        if (optional.isPresent()) {
            Exam exam = optional.get();
            newFillInBlanksQuestion.setExam(exam);
            return fillInBlanksRepository.save(newFillInBlanksQuestion);
        }
        return null;
    }


    @GetMapping("/api/fillInTheBlanks/{questionId}")
    public FillInTheBlanksExamQuestion findFillInBlanksQuestionById(@PathVariable("questionId") int questionId) {
        Optional<FillInTheBlanksExamQuestion> optional = fillInBlanksRepository.findById(questionId);
        return optional.orElse(null);
    }

}

