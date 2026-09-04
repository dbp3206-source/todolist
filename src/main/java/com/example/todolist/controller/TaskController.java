package com.example.todolist.controller;

import com.example.todolist.model.Task;
import com.example.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // tbao  de xu ly http kphai class bthg -> return thang cho client
@RequestMapping("/api/tasks") // dia chi goc cho toan bo endpoint
public class TaskController {
    // Controller ko tu xu ly CRUD ma se gui yeu cau sang Service
    private final TaskService taskService;
    // Constructor Injection: Spring se tao TaskService-> TaskService xu ly va truyen vao TaskController
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //Method
    // READ
    @GetMapping// method nay return thang danh sach Task -> cho thanh JSON-> gui cho client
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    } //GET  /api/tasks

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable Long id) {
        // Controller chi gui yeu cau tim task sang Service
        Task task = taskService.getTaskById(id);
        if (task != null) return ResponseEntity.ok(task); // neu thay task thi tra ve luon
        return ResponseEntity.notFound().build(); // ko thay thi gui status
    }


    @GetMapping("/finished/{finished}")
    public List<Task> getTaskByStatus(@PathVariable boolean finished) {
        return taskService.getTasksByStatus(finished); // nhan 1 list cac task thoa man status va tra ve (Service loc)
    }

    // Search bang tu khoa
    @GetMapping("/search")
    public List<Task> searchTask(@RequestParam(required = false) String title){ // false nen phai xu ly null
        //Controller nhan tu khoa -> Service tim -> Controller return
        return taskService.searchTaskByTitle(title);
    }

    //CREATE
    @PostMapping // POST /api/tasks
    @ResponseStatus(HttpStatus.CREATED) // tbao 201 Created neu thanh cong
    public Task createTask(@RequestBody Task task){
        // Controller nhan Task tu request body -> gui Task sang Service de tao
        return taskService.createTask(task);
    }

    // Cach 2: co the dung ResponseEntity + them header Location sau khi tao Task moi
   /*
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){

        Task createdTask = taskService.createTask(task);

        return ResponseEntity
                .created(URI.create("/api/tasks/" + createdTask.getId()))
                .body(createdTask);
    }
    */

    //UPDATE
    @PutMapping("/{id}")
    // lay id tu URL = @PathVariable
    // method can biet 2 thu: sua task nao (id) va sua thanh gi (RequestBody)
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task newTask){
        // Viec tim task va update tung field da chuyen sang Service -> Controller nhan va return
        Task updatedTask = taskService.updateTask(id, newTask);

        if (updatedTask != null) return ResponseEntity.ok(updatedTask);
        // tim thay thi tra ve status: 200 va body: task
        return ResponseEntity.notFound().build();
        // ko tim thay thi tra ve status 404 (not Found), build la de hoan thien response
    }

    /*
    @PatchMapping("/{id}")
    public ResponseEntity<Task> updatePartially(
        @PathVariable Long id,
        @RequestBody Task newTask
            ) {

        // Khi lam PATCH sau nay:
        // Controller cung chi goi Service
        Task updatedTask = taskService.updatePartially(id, newTask);

        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }

        return ResponseEntity.notFound().build();
    }
     */

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        // Viec removeIf va xoa Task da chuyen sang Service
        boolean deleted = taskService.deleteTask(id);

        //removeIf ko chi xoa ma con return boolean xem co xoa dc ptu nao hay ko
        // hien tai removeIf nam trong Service, Controller chi nhan ket qua true / false
        if(deleted) return ResponseEntity.noContent().build(); // 204 no content
        return ResponseEntity.notFound().build();
    }

}