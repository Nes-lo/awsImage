package com.codingdojo.projectexam.controllers;

import com.codingdojo.projectexam.models.Task;
import com.codingdojo.projectexam.models.User;
import com.codingdojo.projectexam.services.TaskService;
import com.codingdojo.projectexam.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskContoller {

    private TaskService taskService;
    private UserService userService;

    public TaskContoller(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("")
    public  String listBooks(Model model, HttpSession session){

        User user=(User) session.getAttribute("user");

        if(user==null)return "redirect:/";

        model.addAttribute("user", user);
        model.addAttribute("tasks", taskService.findNotCompleted(false));

        return "tasks.jsp";

    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task")Task task, Model model, HttpSession session)
    {
        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }
        model.addAttribute("users",userService.findAll());
        return "newTask.jsp";
    }
    @PostMapping("/new")
    public String addNewTask(@Valid @ModelAttribute("task") Task task, BindingResult result, HttpSession session, Model model) {

        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }
        if(taskService.countAssigne(task.getAssigne())>=3) {
            result.rejectValue("assigne", "Matches", "No se pueden asinar mas de tres tareas a un usuario");
        }

            if(result.hasErrors()) {
            model.addAttribute("users",userService.findAll());
            return "newTask.jsp";
             }else {
                task.setCompleted(false);
                task.setCreator(user);
                taskService.addTask(task);


            }
        return "redirect:/tasks";
    }


    @GetMapping("/{id}")
    public String verTask(@PathVariable("id")Long id,HttpSession session, Model model ){
        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }

        model.addAttribute("user",user);
        model.addAttribute("task",taskService.findById(id));

        return "viewTask.jsp";

    }
    @GetMapping("/{id}/edit")
    public String editTask(@PathVariable("id") Long id, HttpSession session, Model model){
        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }
        Task task=taskService.findById(id);
        if(user.getId()==task.getCreator().getId()){
            model.addAttribute("users",userService.findAll());
            model.addAttribute("task",task);
            return "editTask.jsp";
        }
        else{
            return "redirect:/tasks";
        }
    }

    @PutMapping("/{id}/edit")
    public String editTask(@Valid @ModelAttribute("task") Task task, BindingResult result, HttpSession session, Model model){
        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }
        if(taskService.countAssigne(task.getAssigne())>=3) {
            result.rejectValue("assigne", "Matches", "No se pueden asinar mas de tres tareas a un usuario");
        }
        if(result.hasErrors()) {
            model.addAttribute("users",userService.findAll());
            return "editTask.jsp";
        }
        Task task1=taskService.findById(task.getId());
        if(user.getId()==task1.getCreator().getId()){
            task.setCreator(user);
            task.setCompleted(false);
            taskService.addTask(task);
        }
        return "redirect:/tasks";

    }

    @GetMapping("/{id}/completed")
    public String taskCompleted(@PathVariable("id") Long id, HttpSession session){

        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }

        Task task=taskService.findById(id);
        task.setCompleted(true);
        taskService.addTask(task);

        return "redirect:/tasks/"+id;


    }

    @GetMapping("/highlow")
    public String orderHigh(Model model,HttpSession session){
        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }


        model.addAttribute("user", user);
        model.addAttribute("tasks", taskService.findOrderH());

        return "tasks.jsp";
    }
    @GetMapping("/lowhigh")
    public String orderLow(Model model,HttpSession session){
        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("tasks", taskService.findOrderL());
        return "tasks.jsp";
    }


    @DeleteMapping("/{id}/delete")
    public String taskDelete(@PathVariable("id") Long id, HttpSession session){
        User user=(User) session.getAttribute("user");
        if(user==null){return "redirect:/";
        }
        taskService.deleteTask(id);
        return "redirect:/tasks";

    }





}
