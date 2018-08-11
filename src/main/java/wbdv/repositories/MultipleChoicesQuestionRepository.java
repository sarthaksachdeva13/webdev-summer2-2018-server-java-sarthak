package wbdv.repositories;

import org.springframework.data.repository.CrudRepository;
import wbdv.models.MultipleChoiceExamQuestion;

public interface MultipleChoicesQuestionRepository extends CrudRepository<MultipleChoiceExamQuestion, Integer> {

}