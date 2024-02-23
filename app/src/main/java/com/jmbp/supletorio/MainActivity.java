package com.jmbp.supletorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText v1,v2,v3,v5,v6,v7,v8,v9,v10;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1=findViewById(R.id.editTextTem);
        v2=findViewById(R.id.editTextFeels);
        v3=findViewById(R.id.editTextTmin);
        v5=findViewById(R.id.editTextTmax);
        v6=findViewById(R.id.editTextpressur);
        v7=findViewById(R.id.editTextsee_leave);
        v8=findViewById(R.id.editTextgmd_le);
        v9=findViewById(R.id.editTexthumidity);
        v10=findViewById(R.id.editTexttemp_kf);
        btn1=findViewById(R.id.bTN1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v1.getText().toString().isEmpty() || v2.getText().toString().isEmpty() || v3.getText().toString().isEmpty() || v4.getText().toString().isEmpty() || v5.getText().toString().isEmpty() || v6.getText().toString().isEmpty() || v7.getText().toString().isEmpty() || v8.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Verifica si el contenido de v1 y v5 son números y tienen una longitud máxima de 10 dígitos
                    if (!v1.getText().toString().matches("\\d{1,10}")) {
                        Toast.makeText(MainActivity.this, "El campo cedula debe contener solo números de hasta 10 dígitos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!v5.getText().toString().matches("\\d{1,10}")) {
                        Toast.makeText(MainActivity.this, "El campo telefono debe contener solo números de hasta 10 dígitos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                ConsumirApi();
            }
        });

    }


    public void ConsumirApi() {

//       String url="http://192.168.10.114/ws/webapi.php?op=validar&usuario="+v1.getText()+"&contrasenia="+v2.getText();
        //String url="http://192.168.10.114/ws/webapi.php?op=login&usuario="+v1.getText()+"&contrasenia="+v2.getText();
        String url = "http://192.168.10.112/webPro/webapi.php?op=insertar&cedula=" + v1.getText() + "&nombre=" + v2.getText() + "&apellido=" + v3.getText() + "&fecha=" + v4.getText() + "&telefono=" + v5.getText() + "&correo=" + v6.getText() + "&usuario=" + v7.getText() + "&contrasenia=" + v8.getText();
//        String url="http://192.168.10.114/ws/webapi.php?op=lista";
        OkHttpClient cliente = new OkHttpClient();

        Request get = new Request.Builder().url(url).build();


        cliente.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "Fallo la conexión", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody responseBody = response.body();
                    if (response.isSuccessful()) {
                        respuesta = responseBody.string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this, "Usuario ingresado", Toast.LENGTH_SHORT).show();
                                // Actualiza la interfaz de usuario aquí
                                //res1.setText(respuesta);
                            }
                        });
                    } else {
                        throw new IOException("Error en la respuesta de la API: " + response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
    }
}