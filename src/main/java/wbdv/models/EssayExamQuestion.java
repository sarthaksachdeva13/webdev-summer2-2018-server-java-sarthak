package wbdv.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ESSAY_EXAM_QUESTION")
public class EssayExamQuestion extends BaseExamQuestion {

    @Column(name="ESSAY")
    private
    String essay;

    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }


}
