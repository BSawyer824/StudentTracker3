package com.wgu_android.studenttracker3.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.wgu_android.studenttracker3.Entities.TermsEntity;
import com.wgu_android.studenttracker3.database.StudentTrackerRepository;

import java.util.List;

public class TermViewModel  extends AndroidViewModel {
    private StudentTrackerRepository mRepository;
    private LiveData<List<TermsEntity>> mAllTerms;
    
    public TermViewModel(Application application){
        super(application);
        mRepository=new StudentTrackerRepository(application);
        mAllTerms=mRepository.getAllTerms();
    }

    public LiveData<List<TermsEntity>> getAllTerms(){
        return mAllTerms;
    }

    public void insert(TermsEntity termEntity){
        mRepository.insert(termEntity);
    }

    public int lastID(){
        return mAllTerms.getValue().size();
    }
}
