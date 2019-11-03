package com.wgu_android.studenttracker3.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.wgu_android.studenttracker3.Entities.TermsEntity;

import java.util.List;

/**********************************************************
Database Access Object
Holds the SQL queries to access the data via room
 ********************************************************/

@Dao
public interface TermDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermsEntity term);

    @Query("DELETE FROM terms_table")
    void deleteAll();

    @Query("SELECT * FROM terms_table ORDER BY term_id ASC")
    LiveData<List<TermsEntity>> getAllTerms();
}

