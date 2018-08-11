package wbdv.repositories;

import org.springframework.data.repository.CrudRepository;
import wbdv.models.EssayExamQuestion;

public interface EssayQuestionRepository extends CrudRepository<EssayExamQuestion, Integer> {

}