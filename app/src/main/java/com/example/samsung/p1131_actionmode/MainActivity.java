package com.example.samsung.p1131_actionmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.view.ActionMode.*;
import static android.widget.AbsListView.*;

public class MainActivity extends AppCompatActivity {

    private ActionMode actionMode;
    private Callback callback;
    private ListView lvActionMode;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_activated_1,
                getResources().getStringArray(R.array.data));

        lvActionMode = (ListView) findViewById(R.id.lvActionMode);
        lvActionMode.setAdapter(adapter);
        lvActionMode.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvActionMode.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.context, menu );
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                mode.finish();
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                String message = "position = " + position + ", checked = " + checked;
                Messager.sendToAllRecipients(getBaseContext(), message);
            }
        });
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
