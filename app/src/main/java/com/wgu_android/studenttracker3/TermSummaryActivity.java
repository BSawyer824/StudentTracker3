package com.wgu_android.studenttracker3;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wgu_android.studenttracker3.Entities.TermsEntity;
import com.wgu_android.studenttracker3.ViewModel.TermViewModel;
import com.wgu_android.studenttracker3.ui_adapters.TermAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import java.util.List;

/**********************************************************
 This is the Activity/Screen for the Terms Summary Page
 Users will be able to see any terms they've already been assigned, with associated
 start and end dates.
 Users will also be able to add terms on this screen.
 Finally, users will be able to click on a Term and go to the term detail page
 ********************************************************/

public class TermSummaryActivity extends AppCompatActivity {

    private TermViewModel mTermViewModel;
    public static final int NEW_TERM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //*************************************************
        //FAB - opens the Term Detail Activity, and passes the selected term
        FloatingActionButton fab = findViewById(R.id.fab);
        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermSummaryActivity.this, TermDetailActivity.class);
//                intent.putExtra("productID",mProductViewModel.lastID()+1);
                startActivityForResult(intent, NEW_TERM_ACTIVITY_REQUEST_CODE);
            }
        });

        //**************************************************************************
        //connects the recycler view on the activity to the adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView_TermSummary);
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //*************************************************************************
        //Returns all the terms to display in the Recycler View
        mTermViewModel.getAllTerms().observe(this, new Observer<List<TermsEntity>>() {
            @Override
            public void onChanged(@Nullable final List<TermsEntity> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
//            TermsEntity term = new TermsEntity(mTermViewModel.lastID()+1, data.getStringExtra("term_name"));
//            mTermViewModel.insert(term);
        }
    }
}
