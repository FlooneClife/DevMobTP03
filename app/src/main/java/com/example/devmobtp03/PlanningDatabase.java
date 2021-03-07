package com.example.devmobtp03;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {PlanningRoom.class}, version = 1)
public abstract class PlanningDatabase extends RoomDatabase {

    public abstract PlanningDAO planningDAO();

}