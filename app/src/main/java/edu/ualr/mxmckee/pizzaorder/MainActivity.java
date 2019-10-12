package edu.ualr.mxmckee.pizzaorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButtonToggleGroup btnGroup = findViewById(R.id.toggle_button_group);
        btnGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {

            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                imageView = findViewById(R.id.imageView);
                if (isChecked) {
                    if (checkedId == R.id.round_button) {
                        imageView.setImageResource(R.drawable.ic_round_pizza);
                    }
                    else if (checkedId == R.id.square_button) {
                        imageView.setImageResource(R.drawable.ic_squared_pizza);
                    }
                }
            }
        });
    }
}
