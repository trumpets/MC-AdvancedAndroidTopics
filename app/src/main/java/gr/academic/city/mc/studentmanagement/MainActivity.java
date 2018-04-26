package gr.academic.city.mc.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int CHOOSE_COLOR_REQUEST = 1;

    private StudentsApi studentsApi;

    private ListView lvResults;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StudentsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        studentsApi = retrofit.create(StudentsApi.class);

        lvResults = (ListView) findViewById(R.id.lv_results);
        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (students != null) {
                    Student student = students.get(position);
                    startActivity(StudentDetailsActivity.getIntent(MainActivity.this, student.getFirstName(), student.getLastName(), student.getAge()));
                }
            }
        });
        
        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendStudentToServer();
            }
        });
        
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStudentsFromServer();
            }
        });

        findViewById(R.id.btn_chose_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseColorIntent = new Intent(MainActivity.this, ColorChooserActivity.class);
                startActivityForResult(chooseColorIntent, CHOOSE_COLOR_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_COLOR_REQUEST) {
            if (resultCode == RESULT_OK) {
                // We got a color!
                int chosenColor = data.getIntExtra(ColorChooserActivity.EXTRA_COLOR, -1);
                findViewById(R.id.top_layout).setBackgroundColor(chosenColor);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void sendStudentToServer() {
        String firstName = ((TextView) findViewById(R.id.txt_first_name)).getText().toString();
        String lastName = ((TextView) findViewById(R.id.txt_last_name)).getText().toString();
        String age = ((TextView) findViewById(R.id.txt_age)).getText().toString();

        studentsApi.createStudent(new Student(firstName, lastName, age))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            showToast(R.string.msg_student_created);
                        } else {
                            showToast(R.string.msg_student_not_created);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        showToast(R.string.msg_server_error);
                    }
                });
    }

    private void getStudentsFromServer() {
        studentsApi.getAllStudents()
                .enqueue(new Callback<ListResponse>() {
                    @Override
                    public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                        if (response.isSuccessful()) {
                            students = response.body().getStudents();
                            lvResults.setAdapter(new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1, students));
                        } else {
                            showToast(R.string.msg_students_not_fetched);
                        }
                    }

                    @Override
                    public void onFailure(Call<ListResponse> call, Throwable t) {
                        showToast(R.string.msg_server_error);
                    }
                });
    }

    private void showToast(int msgString) {
        Toast.makeText(this, msgString, Toast.LENGTH_SHORT).show();
    }
}
