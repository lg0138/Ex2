package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class SolveActivity extends AppCompatActivity {
    double aCoeff, bCoeff, cCoeff;
    TextView x1TextView, x2TextView;
    double x1, x2;
    int numSolutions;
    WebView graphWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);

        Intent gi = getIntent();
        aCoeff = gi.getDoubleExtra("aCoeff", 1.0);
        bCoeff = gi.getDoubleExtra("bCoeff", 1.0);
        cCoeff = gi.getDoubleExtra("cCoeff", 1.0);

        x1TextView = findViewById(R.id.x1TextView);
        x2TextView = findViewById(R.id.x2TextView);
        graphWebView = findViewById(R.id.graphWebView);

        solveEquationAndShowResult();
    }

    private void solveEquationAndShowResult() {
        solveEquationAndShow();
        showGraph();
    }

    private void solveEquationAndShow() {
        // x1,2 = (-b +- sqrt(b^2 - 4ac) ) / 2a
        double delta = bCoeff * bCoeff - 4 * aCoeff * cCoeff;

        if(delta < 0) {
            numSolutions = 0;
            Toast.makeText(this, "No solutions for equation",
                    Toast.LENGTH_SHORT).show();
        } else if(delta > 0) {
            numSolutions = 2;
        } else {
            numSolutions = 1;
        }

        if(numSolutions >= 1) { // 1 or 2 solutions
            x1 = (-bCoeff + Math.sqrt(delta)) / 2 * aCoeff;
            x1TextView.setText("X1 = " + x1);

            if(numSolutions == 2) {
                x2 = (-bCoeff - Math.sqrt(delta)) / 2 * aCoeff;
                x2TextView.setText("X2 = " + x2);
            }
        }
    }

    private void showGraph() {
        String stringUrl = "";
        String miniUrl = aCoeff + "x%5E2" ;
        if (bCoeff>0){
            miniUrl = miniUrl +  "%2B" + bCoeff + "x";
        }
        else if(bCoeff<0){
            miniUrl = miniUrl + bCoeff + "x";
        }
        if (cCoeff>0){
            miniUrl = miniUrl + "%2B" + cCoeff;
        }
        else if (cCoeff<0){
            miniUrl = miniUrl + cCoeff;
        }

        stringUrl= "https://www.google.com/search?" +
                "q=" +
                miniUrl + "&oq=" + miniUrl;

        graphWebView.getSettings().setJavaScriptEnabled(true);
        graphWebView.loadUrl(stringUrl);
    }
}
