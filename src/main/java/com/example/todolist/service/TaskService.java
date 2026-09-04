package com.example.todolist.service;

import com.example.todolist.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>(List.of( // tao san list luu cac task
            new Task(
                    1L,
                    "Di ngu",
                    "Di ngu luc 11pm",
                    false,
                    "2/9/2026"
            ),
            new Task(
                    2L,
                    "Di ia",
                    "Di ia sau khi an com",
                    true,
                    "30/1/2020"
            ),
            new Task(
                    3L,
                    "Code",
                    "Code 100 bai codeptit",
                    false,
                    "3/2/2006"
            )
    ));

    private Long nextId = 4L; // lam moc de tu tang id


    // lay toan bo danh sach task
    public List<Task> getAllTasks() {
        return tasks;
    }


    // tim task theo id
    public Task getTaskById(Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }


    // loc task theo trang thai finished
    public List<Task> getTasksByStatus(boolean finished) {
        List<Task> status = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isFinished() == finished) {
                status.add(task);
            }
        }
        return status;
    }


    // tao task moi
    public Task createTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }


    // update task theo id
    public Task updateTask(Long id, Task newTask) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) { //update tung field neu khop
                task.setTitle(newTask.getTitle());
                task.setDescription(newTask.getDescription());
                task.setFinished(newTask.isFinished());
                task.setDeadline(newTask.getDeadline());
                return task;
            }
        }
        return null;
    }


    // xoa task theo id
    public boolean deleteTask(Long id) {
        boolean deleted = tasks.removeIf(task -> task.getId().equals(id)); // ko can duyet het task
        //removeIf ko chi xoa ma con return boolean xem co xoa dc ptu nao hay ko
        return deleted;
    }

    // tim task theo title
    public List<Task> searchTaskByTitle(String title){
        if(title == null || title.isBlank()) return tasks; // neu query parameter rong thi tra ve het

        String keyword = title.toLowerCase();
        //nhan vao String title -> filter (loc ra cac task co title ma chua keyword) -> chuyen thanh list
        return tasks.stream().filter(task->task.getTitle().toLowerCase().contains(keyword)).toList();
    }
}