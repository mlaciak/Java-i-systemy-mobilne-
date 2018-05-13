package Application.Tasks;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String title;
    private String description;
    private Priority priority;
    private LocalDate localDate;
    private String priorityColor="green";

    public Task() {
        this.title = "jakis task";
        this.description = "Jakis opis nanana";
        this.priority = Priority.ASAP;
        this.localDate=LocalDate.of(2002,02,10);
    }
    public Task(String title, String description, Priority priority, LocalDate ld) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.localDate=ld;
        if(priority==Priority.ASAP){
            priorityColor="green";
        }
        else if(priority==Priority.NOW){
            priorityColor="yellow";
        }
        else if(priority==Priority.MAYBENEVER){
            priorityColor="red";
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getPriorityColor() {
        return priorityColor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return title;
    }
}
