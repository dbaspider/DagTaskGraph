package com.wts.dag.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Digraph {

    private static final Logger log = LoggerFactory.getLogger(Digraph.class);

    private Set<Task> tasks;
    private Map<Task, Set<Task>> map;

    public Digraph() {
        this.tasks = new HashSet<Task>();
        this.map = new HashMap<Task, Set<Task>>();
    }

    /**
     * addEdge 添加一个任务依赖关系
     * @param task Task 任务
     * @param prev Task 被依赖的任务
     */
    public void addEdge(Task task, Task prev) {
        if (!tasks.contains(task) || !tasks.contains(prev)) {
            log.error("addEdge: need addTask first {} / {}", task, prev);
            throw new IllegalArgumentException();
        }
        Set<Task> prevs = map.get(task);
        if (prevs == null) {
            prevs = new HashSet<Task>();
            map.put(task, prevs);
        }
        if (prevs.contains(prev)) {
            log.error("addEdge: prevs already has {}", prev);
            throw new IllegalArgumentException();
        }
        prevs.add(prev);
    }

    /**
     * 添加一个任务
     * @param task Task
     */
    public void addTask(Task task) {
        if (tasks.contains(task)) {
            log.error("addTask: tasks already has {}", task);
            throw new IllegalArgumentException();
        }
        tasks.add(task);
    }

    /**
     * 移除一个任务
     * @param task Task
     */
    public void remove(Task task) {
        if (!tasks.contains(task)) {
            return;
        }
        if (map.containsKey(task)) {
            map.remove(task);
        }
        for (Set<Task> set : map.values()) {
            if (set.contains(task)) {
                set.remove(task);
            }
        }
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Map<Task, Set<Task>> getMap() {
        return map;
    }

    public void setMap(Map<Task, Set<Task>> map) {
        this.map = map;
    }
}
