package com.wgu_android.studenttracker3.ui_adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wgu_android.studenttracker3.Entities.TermsEntity;
import com.wgu_android.studenttracker3.R;
import java.util.List;

/**********************************************************
 Recycler View Adapter for the Terms Summary Recycler View
 ********************************************************/

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {

        //************************************************
        //Variable Declarations
        private final TextView termItemView;

        //************************************************
        //Constructors
        private TermViewHolder(View itemView) {
            super(itemView);
            termItemView = itemView.findViewById(R.id.txtViewTermSummary);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final TermsEntity current = mTerms.get(position);
//                    Intent intent = new Intent(context, PartsActivity.class);
//                    intent.putExtra("termName", current.getTermName());
//                    intent.putExtra("termPrice", Double.toString(current.getTermPrice()));
//                    intent.putExtra("termID",current.getTermID());
//                    intent.putExtra("position",position);
//                    context.startActivity(intent);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<TermsEntity> mTerms; // Cached copy of words

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_summary, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        if (mTerms != null) {
            final TermsEntity current = mTerms.get(position);
            holder.termItemView.setText(current.getTermName());
        } else {
            // Covers the case of data not being ready yet.
            holder.termItemView.setText("No Word");
        }
    }



    public void setWords(List<TermsEntity> words) {
        mTerms = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTerms != null)
            return mTerms.size();
        else return 0;
    }    
    
}
