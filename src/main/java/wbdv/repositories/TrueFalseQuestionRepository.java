package wbdv.repositories;

import org.springframework.data.repository.CrudRepository;
import wbdv.models.TrueOrFalseExamQuestion;

public interface TrueFalseQuestionRepository extends CrudRepository<TrueOrFalseExamQuestion, Integer> {

}