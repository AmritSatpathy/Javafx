package sample.model;

public class Task {

    private String description;
    private String task;

    public Task() {
    }

    public Task(String description, String task) {
        this.description = description;
        this.task = task;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
