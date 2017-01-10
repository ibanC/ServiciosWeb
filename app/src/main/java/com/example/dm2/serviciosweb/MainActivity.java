package com.example.dm2.serviciosweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnConvertir;
    private EditText edCantidad;
    private TextView txtResultado;
    private Spinner spinner1,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado=(TextView)findViewById(R.id.txtResultado);
        btnConvertir=(Button)findViewById(R.id.btnConvertir);
        edCantidad=(EditText)findViewById(R.id.edCantidad);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);


        String[] datos=new String[]{"Kilometros","Millas","Centimetros","Metros"};

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        spinner1.setAdapter(adaptador);
        spinner2.setAdapter(adaptador);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String cantidad= edCantidad.getText().toString();

            }
        });
    }
}
