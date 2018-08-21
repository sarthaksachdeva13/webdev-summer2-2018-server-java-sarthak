package wbdv.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam extends Widget {
    private String title;
    private String description;
    private int points;
    @ManyToOne
    @JsonIgnore
    private Lesson lesson;
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
    public Lesson getLesson() {
        return lesson;
    }
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }


}