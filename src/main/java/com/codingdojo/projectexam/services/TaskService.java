package com.codingdojo.projectexam.services;

import com.codingdojo.projectexam.models.Task;
import com.codingdojo.projectexam.models.User;
import com.codingdojo.projectexam.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public List<Task> findNotCompleted(Boolean completed){
        return taskRepository.findByCompleted(completed);
    }
    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Task findById(Long id){
      Optional <Task> task=taskRepository.findById(id);
        if (task.isPresent()){
            return task.get();
        }
        return null;
    }

    public List<Task> findOrderH(){
        return taskRepository.findByPriorityH();
    }
    public List<Task> findOrderL(){
        return taskRepository.findByPriorityL();
    }
    public void deleteTask(Long id){

        taskRepository.deleteById(id);
    }

    public Integer countAssigne(User  user){
        return taskRepository.findByAssigneAndCompleted(user,false).size();    }



}
