package wbdv.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Assignment extends Widget {

    private int points;
    private String title;
    private String description;
    private String href;
    @ManyToOne
    @JsonIgnore
    private Lesson lesson;
    @OneToMany
    @JsonIgnore
    private List<BaseExamQuestion> questions;

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
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
    public Lesson getLesson() {
        return lesson;
    }
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    public List<BaseExamQuestion> getQuestions() {
        return questions;
    }
    public void setQuestions(List<BaseExamQuestion> questions) {
        this.questions = questions;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }


}