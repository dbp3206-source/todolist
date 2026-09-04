package com.example.todolist.model;

public class Task {

    private Long id; // ko de long vi long la primitive, ko the chua null
    private String title;
    private String description;
    private boolean finished;
    private String deadline;


    //Xay dung 1 constructor rong: de co the tao 1 object Task ma chua can truyen du lieu
    public Task() {
    }


    public Task(
            Long id,
            String title,
            String description,
            boolean finished,
            String deadline
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.finished = finished;
        this.deadline = deadline;
    }


    // GETTER VA SETTER cho tung fields

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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


    public boolean isFinished() {
        return finished;
    }


    public void setFinished(boolean finished) {
        this.finished = finished;
    }


    public String getDeadline() {
        return deadline;
    }


    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}