package com.example.dm2.serviciosweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button btnConvertir;
    private EditText edCantidad;
    private TextView txtResultado;
    private Spinner spinner1,spinner2;
    private String valorSpinner1;
    private String valorSpinner2;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado=(TextView)findViewById(R.id.txtResultado);
        btnConvertir=(Button)findViewById(R.id.btnConvertir);
        edCantidad=(EditText)findViewById(R.id.edCantidad);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);


        String[] datos=new String[]{"Kilometers","Yards","Centimeters","Meters","Miles"};

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        spinner1.setAdapter(adaptador);
        spinner2.setAdapter(adaptador);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valorSpinner1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                valorSpinner2 = adapterView.getItemAtPosition(i).toString();
            }
        });
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String cantidad= edCantidad.getText().toString();
                AsyncPost tarea=new AsyncPost();
                tarea.execute(cantidad);
            }
        });
    }

    private class AsyncPost extends AsyncTask<String,Void,Void>
    {

        @Override
        protected Void doInBackground(String... params) {


            try {
                HttpURLConnection conn;
                URL url=new URL("http://www.webservicex.net/length.asmx?op=ChangeLengthUnit");

                //codificamos solo los valores de los parametros
                String param="LengthValue="+ URLEncoder.encode(params[0],"UTF-8")+"&fromLengthUnit="+URLEncoder.encode(valorSpinner1,"UTF-8")
                        +"&toLengthUnit="+URLEncoder.encode(valorSpinner2,"UTF-8");

                conn= (HttpURLConnection) url.openConnection();

                //se estan cargamdo datos post si esta a true
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                //enviar el post
                PrintWriter out=new PrintWriter((conn.getOutputStream()));
                out.print(param);
                out.close();

                //construir la cadena para almacenar la respuesta del servidor
                String result="";
                resultado ="";

                //comenzar a escuchar el stream(flujo)
                Scanner inStream=new Scanner(conn.getInputStream());

                //procesa el stream(flujo) y lo almacena en un String

                while(inStream.hasNextLine())
                {
                    result=inStream.nextLine();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
