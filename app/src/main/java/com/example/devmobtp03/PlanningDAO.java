package com.example.devmobtp03;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanningDAO {

    @Query("SELECT * FROM PlanningRoom")
    List<PlanningRoom> getAll();

    @Query("SELECT hourBegin FROM PlanningRoom")
    List<Integer> getAllHoursBegin();

    @Query("SELECT hourEnd FROM PlanningRoom")
    List<Integer> getAllHoursEnd();

    @Query("SELECT task FROM PlanningRoom")
    List<String> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PlanningRoom... plannings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlanningRoom planning);

    @Delete
    void delete(PlanningRoom planningRoom);

}
