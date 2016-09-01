package py.edu.facitec.gestordetareas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText nombreEditText;
    EditText emailEditText;
    Button guardarButton;

    ListView usuarioListView;

    List<String> usuarios = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //downcast and upcast
        nombreEditText = (EditText) findViewById(R.id.editTextNombre);
        emailEditText = (EditText) findViewById(R.id.editTextEmail);
        guardarButton = (Button) findViewById(R.id.buttonGuardar);

        //Para crear la lista
        usuarioListView = (ListView)findViewById(R.id.listViewUsuarios);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,usuarios);

        usuarioListView.setAdapter(adapter);

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nombreEditText.getText().toString();
                String email = emailEditText.getText().toString();

                //Todo validacion
                if(!validarNombre(nombre)) {
                    //tratar el error
                    nombreEditText.setError(getString(R.string.nombre_error));

                }else if(!validarEmail(email)){
                    emailEditText.setError(getString(R.string.error_email));

                }else{
                    String mensaje =  getString(R.string.welcome_msj)+" "+nombre+" "+email;
                    Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG).show();

                    //
                    String datos = nombre+" "+email;
                    usuarios.add(datos);
                    adapter.notifyDataSetChanged();



                    nombreEditText.setText(null);
                    emailEditText.setText(null);
                }

            }
        });

    }
    private boolean validarNombre(String nombre){

        return !nombre.equals("");
    }

    private boolean validarEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();


    }

}
