package com.example.mstudent.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mstudent.R;
import com.example.mstudent.adapters.PrimaryExamsAdapter;
import com.example.mstudent.api.NoAuthClient;
import com.example.mstudent.models.PrimaryExam;
import com.example.mstudent.models.Student;
import com.example.mstudent.models.StudentDetails;
import com.example.mstudent.storage.StudentSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrimarySchoolActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PrimaryExam> primaryExamList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_school);

        primaryExamList = new ArrayList<>();
        Student student = StudentSharedPref.getInstance(getApplicationContext()).getStudent();

        recyclerView = findViewById(R.id.results_recycler);

        getResults();
        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.results_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Primary School Results");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getResults() {
        Student student = StudentSharedPref.getInstance(getApplicationContext()).getStudent();

        Call<StudentDetails> call = NoAuthClient.getInstance().getApi().studentDetails(student.getId());
        call.enqueue(new Callback<StudentDetails>() {
            @Override
            public void onResponse(Call<StudentDetails> call, Response<StudentDetails> response) {
                primaryExamList = response.body().getPrimaryExams();
                PrimaryExamsAdapter primaryExamsAdapter = new PrimaryExamsAdapter(getApplicationContext(), primaryExamList);
                primaryExamsAdapter.setPrimaryExamList(primaryExamList);
                recyclerView.setAdapter(primaryExamsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onFailure(Call<StudentDetails> call, Throwable t) {
                Toast.makeText(PrimarySchoolActivity.this, "an error occurred", Toast.LENGTH_SHORT).show();
                Log.e("Primary", "onFailure: " + t.getMessage() );

            }
        });


    }
}
