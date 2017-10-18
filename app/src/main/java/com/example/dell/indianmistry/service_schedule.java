package com.example.dell.indianmistry;

/**
 * Created by DELL on 18-10-2017.
 */

public class service_schedule {

    String problem_desc,date,time;
    public service_schedule() {

    }

    public String getProblem_desc() {
        return problem_desc;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public service_schedule(String problem_desc, String date, String time) {
        this.problem_desc = problem_desc;
        this.date = date;

        this.time = time;
    }
}
