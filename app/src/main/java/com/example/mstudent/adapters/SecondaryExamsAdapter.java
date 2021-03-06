package com.example.mstudent.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mstudent.R;
import com.example.mstudent.models.SecondaryExam;

import java.util.List;

public class SecondaryExamsAdapter extends RecyclerView.Adapter<SecondaryExamsAdapter.ViewHolder> {
    private Context context;
    private List<SecondaryExam> secondaryExamList;

    public SecondaryExamsAdapter(Context context, List<SecondaryExam> secondaryExamList) {
        this.context = context;
        this.secondaryExamList = secondaryExamList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.primary_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SecondaryExam secondaryExam = secondaryExamList.get(position);

        holder.tvSchool.setText(secondaryExam.getSchool());
        Log.e("Score", "onBindViewHolder: " + secondaryExam.getTotalscore() );
        holder.tvScoreValue.setText(String.valueOf(secondaryExam.getTotalscore()));
        holder.tvKlass.setText(String.format("Level: %s", secondaryExam.getKlass()));
    }

    @Override
    public int getItemCount() {
        return secondaryExamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView primaryCard;
        TextView tvSchool, tvScore, tvScoreValue, tvKlass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            primaryCard = itemView.findViewById(R.id.primary_item_card);
            primaryCard.setOnClickListener(this);

            tvSchool = itemView.findViewById(R.id.school_name);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvScoreValue = itemView.findViewById(R.id.tv_score_value);
            tvKlass = itemView.findViewById(R.id.student_klass);
        }

        @Override
        public void onClick(View v) {



        }
    }
}
