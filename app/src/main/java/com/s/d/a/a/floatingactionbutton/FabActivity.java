package com.s.d.a.a.floatingactionbutton;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FabActivity extends AppCompatActivity {
    ArrayList<String> listaDeItens = new ArrayList<String>();
    ArrayAdapter<String> adaptador;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbActivityFab);
        setSupportActionBar(toolbar);

        lstView = findViewById(R.id.listView);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDeItens);
        lstView.setAdapter(adaptador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acrescentarItemNaLista();
                Snackbar.make(view, "Item acrescentado na lista", Snackbar.LENGTH_LONG)
                        .setAction("Undo", undoOnClickListener).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void acrescentarItemNaLista(){
        SimpleDateFormat formatoData = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
        listaDeItens.add(formatoData.format(new Date()));
        adaptador.notifyDataSetChanged();

    }

    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listaDeItens.remove(listaDeItens.size() -1);
            adaptador.notifyDataSetChanged();
            Snackbar.make(view, "Item removido!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };
}
