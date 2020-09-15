package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText aCoeffEditText, bCoeffEditText, cCoeffEditText;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aCoeffEditText = findViewById(R.id.aCoeffEditText);
        bCoeffEditText = findViewById(R.id.bCoeffEditText);
        cCoeffEditText = findViewById(R.id.cCoeffEditText);
        random = new Random();
    }

    public void onRandomButton(View view) {
        aCoeffEditText.setText((random.nextInt(10000) - 5000) + "");
        bCoeffEditText.setText((random.nextInt(10000) - 5000) + "");
        cCoeffEditText.setText((random.nextInt(10000) - 5000) + "");
    }

    public void onSolveButton(View view) {
        if(validInput() == false) {
            Toast.makeText(this, "Please enter all coefficients!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double aCoeff = Double.parseDouble(aCoeffEditText.getText().toString());
        double bCoeff = Double.parseDouble(bCoeffEditText.getText().toString());
        double cCoeff = Double.parseDouble(cCoeffEditText.getText().toString());

        Intent si = new Intent(this, SolveActivity.class);
        si.putExtra("aCoeff", aCoeff);
        si.putExtra("bCoeff", bCoeff);
        si.putExtra("cCoeff", cCoeff);
        startActivity(si);
    }

    private boolean validInput() {
        return aCoeffEditText.getText().toString().length() > 0
                && bCoeffEditText.getText().toString().length() > 0
                && cCoeffEditText.getText().toString().length() > 0;
    }
}
