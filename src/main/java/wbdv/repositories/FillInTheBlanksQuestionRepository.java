package wbdv.repositories;

import org.springframework.data.repository.CrudRepository;
import wbdv.models.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksQuestionRepository extends CrudRepository<FillInTheBlanksExamQuestion, Integer> {

}
