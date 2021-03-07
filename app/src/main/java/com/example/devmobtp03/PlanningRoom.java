package com.example.devmobtp03;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlanningRoom {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "hourBegin")
    public int hourBegin;

    @ColumnInfo(name = "hourEnd")
    public int hourEnd;

    @ColumnInfo(name = "task")
    public String task;

    public PlanningRoom(int id, int hourBegin, int hourEnd, String task) {
        this.id = id;
        this.hourBegin = hourBegin;
        this.hourEnd = hourEnd;
        this.task = task;
    }

}
