package wbdv.repositories;

import wbdv.models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Query("SELECT c from Course c ORDER BY c.title")
    List<Course> sortedCourses();
}
