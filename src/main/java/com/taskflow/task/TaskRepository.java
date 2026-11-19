package com.taskflow.task;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple in-memory store so the app runs with zero database setup on Day 1.
 *
 * In Phase 3 you will delete this class and replace it with a Spring Data
 * interface:  public interface TaskRepository extends JpaRepository<Task, Long> {}
 * The service layer below will keep working with almost no change.
 */
@Repository
public class TaskRepository {

    private final ConcurrentHashMap<Long, Task> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    public List<Task> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(sequence.incrementAndGet());
            task.setCreatedAt(Instant.now());
        }
        store.put(task.getId(), task);
        return task;
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
