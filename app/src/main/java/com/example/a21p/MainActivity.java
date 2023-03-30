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

        // Set up the spinners with the available units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        // Set up the converter with the conversion factors
        converter = new UnitConverter();

        // Set up the listener for the spinners
        fromUnitSpinner.setOnItemSelectedListener(this);
        toUnitSpinner.setOnItemSelectedListener(this);
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