package com.example.a21p;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private EditText inputEditText;
    private TextView resultTextView;
    private UnitConverter converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromUnitSpinner = findViewById(R.id.from_unit_spinner);
        toUnitSpinner = findViewById(R.id.to_unit_spinner);
        inputEditText = findViewById(R.id.input_edit_text);
        resultTextView = findViewById(R.id.result_text_view);

        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toUnitSpinner.setAdapter(toAdapter);


        // Set up the spinners with the available units
        ArrayAdapter<CharSequence> adapterFrom = ArrayAdapter.createFromResource(this,
                R.array.units, android.R.layout.simple_spinner_item);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromUnitSpinner.setAdapter(adapterFrom);

        // Set up the converter with the conversion factors
        converter = new UnitConverter();

        // Add a listener to the first spinner
        fromUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected unit from the first spinner
                String fromUnit = parent.getItemAtPosition(position).toString();

                // Update the items of the second spinner based on the selected unit
                switch (fromUnit) {
                    case "m":
                    case "cm":
                    case "km":
                    case "in":
                    case "ft":
                    case "yd":
                    case "mi":
                        // If the selected unit is a distance unit, show distance units in the second spinner
                        ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(
                                MainActivity.this, R.array.distance, android.R.layout.simple_spinner_item);
                        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        toUnitSpinner.setAdapter(distanceAdapter);
                        break;

                    case "kg":
                    case "t":
                    case "lb":
                    case "oz":
                        // If the selected unit is a weight unit, show weight units in the second spinner
                        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(
                                MainActivity.this, R.array.weight, android.R.layout.simple_spinner_item);
                        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        toUnitSpinner.setAdapter(weightAdapter);
                        break;

                    case "c":
                    case "f":
                    case "k":
                        // If the selected unit is a temperature unit, show temperature units in the second spinner
                        ArrayAdapter<CharSequence> tempAdapter = ArrayAdapter.createFromResource(
                                MainActivity.this, R.array.temp, android.R.layout.simple_spinner_item);
                        tempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        toUnitSpinner.setAdapter(tempAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // When a unit is selected, update the result
        updateResult();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    public void onConvertButtonClick(View view) {
        // When the Convert button is clicked, update the result
        updateResult();
    }

    private void updateResult() {

        EditText inputEditText = findViewById(R.id.input_edit_text);
        if (inputEditText.getText().toString().isEmpty()) {
            TextView resultTextView = findViewById(R.id.result_text_view);
            resultTextView.setText("Please enter a value.");
            return;
        }
        double inputValue = Double.parseDouble(inputEditText.getText().toString());
        // Get the selected units and input value
        String fromUnit = (String) fromUnitSpinner.getSelectedItem();
        String toUnit = (String) toUnitSpinner.getSelectedItem();
        String input = inputEditText.getText().toString();

        // Convert the input value and update the result
        double result = converter.convert(fromUnit, toUnit, input);
        resultTextView.setText(String.format("%.2f", result));
    }
}