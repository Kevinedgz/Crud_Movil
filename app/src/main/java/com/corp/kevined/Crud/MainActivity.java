package com.corp.kevined.Crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.corp.kevined.Crud.modelos.Dbhelper;
import com.corp.kevined.Crud.modelos.Persona;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn_crear,btn_editar,btn,btn_insertar,btn_eliminar;
    EditText ed_nombre, ed_apellido,ed_correo,ed_fecha;
    ListView lis_view;
    Dbhelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper =new Dbhelper(MainActivity.this);
        btn_crear=findViewById(R.id.btn_crear);
        btn_editar=findViewById(R.id.btn_editar);
        btn_insertar=findViewById(R.id.btn_insertar);
        btn_eliminar=findViewById(R.id.btn_eliminar);
        ed_nombre=findViewById(R.id.ed_nombre);
        ed_apellido=findViewById(R.id.ed_apellido);
        ed_correo=findViewById(R.id.ed_correo);
        ed_fecha=findViewById(R.id.ed_fecha);
        lis_view=findViewById(R.id.lis_view);
        //listenrs
        btn_crear.setOnClickListener(K -> insertar());
        showAll(dbHelper);

    }
    private void showAll(Dbhelper dbHelper) {
        ArrayAdapter everyone = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dbHelper.listAll());
        lis_view.setAdapter(everyone);
    }
        public void insertar(){
        try {
            Persona persona=new Persona(ed_nombre.getText().toString(),ed_apellido.getText().toString(),ed_correo.getText().toString(),ed_correo.getText().toString());
            Dbhelper helper=new Dbhelper(MainActivity.this);
            boolean per=helper.insertar(persona);
            if (per) {
                Toast.makeText(MainActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                showAll(dbHelper);
            }else{Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_SHORT).show();}
        }catch(Exception e ){Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_SHORT).show();


        }
}
}