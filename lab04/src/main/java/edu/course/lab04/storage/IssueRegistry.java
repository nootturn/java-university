package edu.course.lab04.storage;

import edu.course.lab04.projectTask.ProjectTask;

import java.util.HashMap;
import java.util.Map;

public class IssueRegistry {
    private final Map<IssueId, ProjectTask> storage = new HashMap<>();

    public void put(IssueId id , ProjectTask task){
        if (id == null || task == null){
            throw new IllegalArgumentException("id and task must not be null");
        }

        storage.put(id, task);
    }

    public ProjectTask getTaskById(IssueId id){
        return storage.get(id);
    }

    public ProjectTask deleteTaskById(IssueId id){
        return storage.remove(id);
    }

    // возращение статуса удаления логическим значением
    // выводит false при отсутствии ключа в мапе
    public boolean deleteTaskById_returnBoolean(IssueId id){
        boolean isRemoved = storage.containsKey(id);

        if (isRemoved){
            storage.remove(id);
        }

        return isRemoved;
    }

    public boolean contains(IssueId id){
        return storage.containsKey(id);
    }

    public int size(){
        return storage.size();
    }

}
