package com.example.samsung.p1131_actionmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static android.view.ActionMode.*;

public class MainActivity extends AppCompatActivity {

    ActionMode actionMode;
    private Callback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callback = new Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.context, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                String message = "item: " + item.getTitle();
                Messager.sendToAllRecipients(getBaseContext(), message);
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                String message = "destroy ActionMode";
                Messager.sendToAllRecipients(getBaseContext(), message);
                actionMode = null;
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClockBtn(View view) {
        if (actionMode == null) {
            actionMode = startActionMode(callback);
        } else {
            actionMode.finish();
        }
    }
}
