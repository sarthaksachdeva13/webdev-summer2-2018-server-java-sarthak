package wbdv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Exam extends Widget {

    private String title;
    private String description;
    private int points;
    @ManyToOne
    @JsonIgnore
    private Topic topic;
    @OneToMany(mappedBy="exam")
    @JsonIgnore
    private List<BaseExamQuestion> questions;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public List<BaseExamQuestion> getQuestions() {
        return questions;
    }
    public void setQuestions(List<BaseExamQuestion> questions) {
        this.questions = questions;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}