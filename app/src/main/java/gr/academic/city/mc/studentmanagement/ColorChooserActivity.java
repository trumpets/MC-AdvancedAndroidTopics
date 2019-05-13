package gr.academic.city.mc.studentmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by trumpets on 5/12/16.
 */
public class ColorChooserActivity extends AppCompatActivity {

    public static final String EXTRA_COLOR = "chosen.color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_chooser);

        findViewById(R.id.btn_submit_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chosenColor;

                RadioGroup colorsGroup = (RadioGroup) findViewById(R.id.rg_colors);
                switch (colorsGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_red:
                        chosenColor = Color.RED;
                        break;

                    case R.id.rb_green:
                        chosenColor = Color.GREEN;
                        break;

                    case R.id.rb_blue:
                        chosenColor = Color.BLUE;
                        break;

                    default:
                        throw new UnsupportedOperationException("No implementation for radio button with id " + colorsGroup.getCheckedRadioButtonId());
                }

                Intent result = new Intent();
                result.putExtra(EXTRA_COLOR, chosenColor);
                setResult(RESULT_OK, result);

                finish();
            }
        });
    }
}