package edu.ualr.mxmckee.pizzaorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {

    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Define a function updatePizzaShape (creates ToggleGroup onButtonCheckedListener) to use ToggleGroup to set ImageView*/
        MaterialButtonToggleGroup btnGroup = findViewById(R.id.toggle_button_group);
        ImageView imageView = findViewById(R.id.imageView);
        updatePizzaShape(btnGroup, imageView);

        /*Define ChipGroup to be modified by CheckBoxes and RadioGroup buttons*/
        chipGroup = findViewById(R.id.chipGroup);

        /*Define a function addOrRemoveToppings (creates CheckBox onCheckedChangeListener) to update ChipGroup to reflect checked items*/
        final MaterialCheckBox pepperoni_checkbox = findViewById(R.id.pepperoni_checkbox);
        final MaterialCheckBox mushrooms_checkbox = findViewById(R.id.mushrooms_checkbox);
        final MaterialCheckBox veggies_checkbox = findViewById(R.id.veggies_checkbox);
        final MaterialCheckBox anchovies_checkbox = findViewById(R.id.anchovies_checkbox);
        addOrRemoveTopping(pepperoni_checkbox);
        addOrRemoveTopping(mushrooms_checkbox);
        addOrRemoveTopping(veggies_checkbox);
        addOrRemoveTopping(anchovies_checkbox);

        /*Define a function adjustCheeseAmount (creates RadioGroup onCheckedChangeListener) to update ChipGroup to reflect current selection*/
        RadioGroup cheeseAmountRG = findViewById(R.id.cheese_amount);
        adjustCheeseAmount(cheeseAmountRG);
    }

    private void updatePizzaShape(MaterialButtonToggleGroup btnGroup, final ImageView imageView) {
        btnGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {

            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.round_button) {
                        imageView.setImageResource(R.drawable.ic_round_pizza);
                    } else if (checkedId == R.id.square_button) {
                        imageView.setImageResource(R.drawable.ic_squared_pizza);
                    }
                }
            }
        });
    }

    private void addOrRemoveTopping(MaterialCheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    addChipToChipGroup(compoundButton.getText().toString(), chipGroup);
                }
                else {
                    removeChipFromChipGroup(compoundButton.getText().toString(), chipGroup);
                }
            }
        });
    }

    private void adjustCheeseAmount(RadioGroup cheeseAmountRG) {
        cheeseAmountRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                String cheeseNone = getString(R.string.no_cheese);
                String cheeseRegular = getString(R.string.cheese);
                String cheeseDouble = getString(R.string.two_times_cheese);

                int id = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(id);
                int radioId = radioGroup.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
                String current_amount = (String) btn.getText();

                removeChipFromChipGroup(cheeseNone, chipGroup);
                removeChipFromChipGroup(cheeseRegular, chipGroup);
                removeChipFromChipGroup(cheeseDouble, chipGroup);

                addChipToChipGroup(current_amount, chipGroup);
            }
        });
    }

    private void addChipToChipGroup(String chipText, ChipGroup chipGroup) {
        Chip chip = new Chip(MainActivity.this);
        chip.setText(chipText);
        chip.setTag(chipText);
        chip.setChipBackgroundColorResource(R.color.primaryColor);
        chip.setTextAppearanceResource(R.style.chipTextAppearance);
        chipGroup.addView(chip);
    }

    private void removeChipFromChipGroup(String chipTag, ChipGroup chipGroup) {
        chipGroup.removeView(chipGroup.findViewWithTag(chipTag));
    }
}