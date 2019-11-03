package com.wgu_android.studenttracker3.database;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.wgu_android.studenttracker3.DAO.TermDAO;
import com.wgu_android.studenttracker3.Entities.TermsEntity;


/**********************************************************
 This instantiates the database, and maintains i tin persistant storage
 The subclass PopulateDbAsync also starts the database with one term as a default
 TODO when I have all the add buttons working, will need to remove the default term
 ********************************************************/


@Database(entities = {TermsEntity.class}, version = 1, exportSchema = false)
//@TypeConverters({ConvertTypes.class})
public abstract class StudentTrackerDatabase extends RoomDatabase {

    //***************************************************************
    //Variable Declaration
    public abstract TermDAO termDao();
    private static volatile StudentTrackerDatabase INSTANCE;

    //***************************************************************
    //Constructor
    static StudentTrackerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentTrackerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        StudentTrackerDatabase.class, "student_tracker_database")
                        .addCallback(sRoomDatabaseCallback)
                        .build();
                }
            }
        }
        return INSTANCE;
    }

    //***************************************************************
    //This callback method will run when the DB is opened
    //it runs the method to populate the db asynchronously
    private static RoomDatabase.Callback sRoomDatabaseCallback =
        new RoomDatabase.Callback(){
            @Override
            public void onOpen (@NonNull SupportSQLiteDatabase db){
                super.onOpen(db);
                new PopulateDbAsync(INSTANCE).execute();
            }
        };

    //***************************************************************
    //This method populates the database asynchronously
    //also currently deletes all data at start up and populates a default value
    //will need to change once more screens are built
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TermDAO mTermDao;
        PopulateDbAsync(StudentTrackerDatabase db) {
            mTermDao = db.termDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mTermDao.deleteAll();
            TermsEntity term=new TermsEntity("Term 1");
            mTermDao.insert(term);
            return null;
        }
    }


}

