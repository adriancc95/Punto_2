package com.adriancastano.sesion1_interfaces;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private String nombre, apellido, correo, sexo, fecha, ciudad, hobbies, password1, password2 = "";
    private EditText eNombre, eApellido, eCorreo, ePassword, ePassword2, eDP;
    private Button bRegistar;
    private TextView tInformacion;
    private RadioButton rMasculino, rFemenino;
    private CheckBox cCine, cComer, cDormir, cBailar;
    private Spinner sCiudades;

    private int dia, mes, ano, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre = (EditText) findViewById(R.id.eNombre);
        eApellido = (EditText) findViewById(R.id.eApellido);
        eCorreo = (EditText) findViewById(R.id.eCorreo);
        ePassword = (EditText) findViewById(R.id.ePassword);
        ePassword2 = (EditText) findViewById(R.id.ePassword2);

        rMasculino = (RadioButton) findViewById(R.id.rMasculino);
        rFemenino = (RadioButton) findViewById(R.id.rFemenino);
        cBailar = (CheckBox) findViewById(R.id.cBailar);
        cComer = (CheckBox) findViewById(R.id.cComer);
        cDormir = (CheckBox) findViewById(R.id.cDormir);
        cCine = (CheckBox) findViewById(R.id.cCine);
        sCiudades = (Spinner) findViewById(R.id.sCiudades);

        bRegistar = (Button) findViewById(R.id.bRegistrar);
        tInformacion = (TextView) findViewById(R.id.tInformacion);

        eDP = (EditText) findViewById(R.id.eDP);
        eDP.setOnFocusChangeListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ciudades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudades.setAdapter(adapter);


        bRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 0;
                hobbies = "";

                if (eNombre.getText().toString().equals(""))
                    a++;
                if (eApellido.getText().toString().equals(""))
                    a++;
                if (ePassword.getText().toString().equals(""))
                    a++;
                if (ePassword2.getText().toString().equals(""))
                    a++;
                if (eCorreo.getText().toString().equals(""))
                    a++;
                if (eDP.getText().toString().equals(""))
                    a++;

                if (cCine.isChecked())
                    hobbies += "Cine ";
                if (cBailar.isChecked())
                    hobbies += "Bailar ";
                if (cDormir.isChecked())
                    hobbies += "Dormir ";
                if (cComer.isChecked())
                    hobbies += "Comer ";

                password1 = ePassword.getText().toString();
                password2 = ePassword2.getText().toString();

                if (a != 0)
                    tInformacion.setText("Ingrese todos los datos");

                else if (password1.equals(password2)) {
                    nombre = eNombre.getText().toString();
                    apellido = eApellido.getText().toString();
                    correo = eCorreo.getText().toString();
                    ciudad = sCiudades.getSelectedItem().toString();
                    fecha = eDP.getText().toString();

                    if (rMasculino.isChecked()) {
                        sexo = "Masculino";
                    } else {
                        sexo = "Femenino";
                    }

                    tInformacion.setText("Nombre: " + nombre + " \nApellido: " + apellido + " \nCorreo: " + correo + " \nSexo: " + sexo + "\nFecha de nacimiento: " + fecha + "\nCiudad: " + ciudad + "\nHobbies: " + hobbies);
                } else
                    tInformacion.setText("Las contraseñas no son las mismas");

            }

        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            final java.util.Calendar c = java.util.Calendar.getInstance();
            dia = c.get(java.util.Calendar.DAY_OF_MONTH);
            mes = c.get(java.util.Calendar.MONTH);
            ano = c.get(java.util.Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    eDP.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();
        }
    }
}

