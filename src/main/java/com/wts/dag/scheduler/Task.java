package com.wts.dag.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task implements Executor {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    private Long id; // 任务id
    private String name; // 任务名称
    private int state; // 任务状态 0: 未执行， 1: 已执行

    public Task(Long id, String name, int state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public boolean execute() {
        log.info("Task id: [" + id + "], " + "task name: [" + name +"] is running");
        state = 1;
        return true;
    }

    public boolean hasExecuted() {
        return state == 1;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
