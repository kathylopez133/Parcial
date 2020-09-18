package com.example.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtCantida, txtCantiCaja, txtResp;

    conversores miConversor = new conversores();
    Spinner spnDe, spnA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tbconversor = (TabHost) findViewById(R.id.tbConversor);
        tbconversor.setup();

        tbconversor.addTab(tbconversor.newTabSpec("tab1").setIndicator("Conversor Universal", null).setContent(R.id.tabUniversal));
        tbconversor.addTab(tbconversor.newTabSpec("tab2").setIndicator("Conversor De Area", null).setContent(R.id.tabArea));

        txtCantida = (EditText) findViewById(R.id.txtCant1);
        txtCantiCaja = (EditText) findViewById(R.id.txtCant2);
        txtResp = (EditText) findViewById(R.id.txtCant3);
        final Spinner spnTipo = (Spinner)findViewById(R.id.spnTipo);
        spnDe = (Spinner)findViewById(R.id.spnDe);
        spnA = (Spinner)findViewById(R.id.spna);

        spnTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                spnDe.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,miConversor.obtenerConversor(position) ));
                spnA.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,miConversor.obtenerConversor(position) ));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Button btnConvertir = (Button)findViewById(R.id.btnConvertir);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tempVal = (TextView)findViewById(R.id.txtcantidad);
                double cantidad = Double.parseDouble(tempVal.getText().toString());
                int d = spnDe.getSelectedItemPosition();
                int a = spnA.getSelectedItemPosition();
                int tipo = spnTipo.getSelectedItemPosition();

                Double respuesta = miConversor.convertir(tipo,d,a,cantidad);
                tempVal = (TextView)findViewById(R.id.lblrespuesta);
                tempVal.setText("Respuesta: "+ respuesta);
            }
        });
    }

    public void conver(View view) {
        //declaracion de variables y las instanciamos con el valor 0 por defecto
        int contadorR = 0;
        int resp = 0;
        int total = 0;
        //obtenemos la cantidad x caja
        int contadorC = Integer.parseInt(txtCantiCaja.getText().toString());


        if (!txtCantida.getText().toString().isEmpty() && !txtCantiCaja.getText().toString().isEmpty()) {
            //obtenemos la cantidad del monto
            int contadorM = Integer.parseInt(txtCantida.getText().toString());
            //ciclo for recores el ciclico para encontrar el resultado
            for (int i = total; i <= contadorM; i++) {
                if (contadorM >= contadorC) {
                    resp = contadorM - contadorC;
                    contadorR += 1;
                    contadorM = resp;
                }
            }
            //seteamos la respuesta en el caja de texto
            txtResp.setText(contadorR + "/" + resp);

        } else if (txtResp.getText().toString().contains("/")) {
            //realizamos un split para obtene las 2 posiciones de los numeros
            String[] separated = txtResp.getText().toString().split("/");

            int pos0 = Integer.parseInt(separated[0]);
            int pos1 = Integer.parseInt(separated[1]);
            //realizamos la operacion
            int Resultado = contadorC * pos0 + pos1;
            txtCantida.setText(String.valueOf(Resultado));
        }
    }
}