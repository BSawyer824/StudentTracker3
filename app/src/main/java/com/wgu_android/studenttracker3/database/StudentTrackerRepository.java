package com.wgu_android.studenttracker3.database;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.wgu_android.studenttracker3.DAO.TermDAO;
import com.wgu_android.studenttracker3.Entities.TermsEntity;
import java.util.List;

/**********************************************************
 Repository handles data operations
 *********************************************************/


public class StudentTrackerRepository {

    //******************************************
    //Variable Declarations
    private TermDAO mTermDAO;
    private LiveData<List<TermsEntity>> mAllTerms;
    private int termID;

    //******************************************
    //Constructors
    public StudentTrackerRepository(Application application){
        StudentTrackerDatabase db=StudentTrackerDatabase.getDatabase(application);
        mTermDAO=db.termDao();
        mAllTerms=mTermDAO.getAllTerms();
    }

    //******************************************
    //Accessors/Getters
    public LiveData<List<TermsEntity>> getAllTerms(){
        return mAllTerms;
    }


    //******************************************
    //Mutators/Setters
    public void insert (TermsEntity termEntity) {
        //inserts a new object into the database via the DAO
        new insertAsyncTask2(mTermDAO).execute(termEntity);
    }

    private static class insertAsyncTask2 extends AsyncTask<TermsEntity, Void, Void> {

        private TermDAO mAsyncTaskDao;
        insertAsyncTask2(TermDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TermsEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
