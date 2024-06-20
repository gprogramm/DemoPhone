package com.android.demophone.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.demophone.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    /**
     * Initialize the components used in xml
     */
    private void initComponents() {

    }
}