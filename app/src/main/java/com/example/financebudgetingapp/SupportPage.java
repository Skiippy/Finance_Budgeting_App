package com.example.financebudgetingapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.TextView;

public class SupportPage extends AppCompatActivity {

    ImageButton expandText = (ImageButton) findViewById(R.id.btnExpandText);
    TextView descriptionText = (TextView) findViewById(R.id.descriptionText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_page);

        expandText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptionText.setMaxLines(Integer.MAX_VALUE);
            }
        });

    }

}