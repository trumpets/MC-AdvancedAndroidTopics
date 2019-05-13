package gr.academic.city.mc.studentmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by trumpets on 5/11/16.
 */
public class StudentDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_FIRST_NAME = "first_name";
    private static final String EXTRA_LAST_NAME = "last_name";
    private static final String EXTRA_AGE = "age";

    // This is a good practice to ensure that your Activity can get all the extras in an easy way
    public static Intent getIntent(Context context, String firstName, String lastName, String age) {
        Intent intent = new Intent(context, StudentDetailsActivity.class);
        intent.putExtra(EXTRA_FIRST_NAME, firstName);
        intent.putExtra(EXTRA_LAST_NAME, lastName);
        intent.putExtra(EXTRA_AGE, age);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra(EXTRA_FIRST_NAME);
        String lastName = intent.getStringExtra(EXTRA_LAST_NAME);
        String age = intent.getStringExtra(EXTRA_AGE);

        TextView tvFirstName = (TextView) findViewById(R.id.tv_first_name);
        TextView tvLastName = (TextView) findViewById(R.id.tv_last_name);
        TextView tvAge = (TextView) findViewById(R.id.tv_age);

        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvAge.setText(age);
    }
}
