package com.codingdojo.projectexam.repositories;

import com.codingdojo.projectexam.models.Task;
import com.codingdojo.projectexam.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
    List<Task> findAll();
    List<Task> findByCompleted(Boolean completed);
    Optional<Task> findById(Long id);

    List<Task> findByAssigneAndCompleted(User user,Boolean completed);

    @Query(value="SELECT * FROM tasks WHERE completed='false' ORDER BY priority ASC ",nativeQuery = true)
    List<Task> findByPriorityH();

    @Query(value="SELECT * FROM tasks WHERE completed='false' ORDER BY priority Desc ",nativeQuery = true)
    List<Task> findByPriorityL();


}
