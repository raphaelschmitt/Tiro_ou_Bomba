package com.schmitt.tiro_ou_bomba;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.schmitt.tiro_ou_bomba.Objetos.Configuracao;

public class Configuracoes extends AppCompatActivity {

    private Toolbar mToolbar;

    private EditText etNumDigitos;
    private Switch swRepetirNumeral;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Configurações");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNumDigitos = (EditText)findViewById(R.id.etNumDigitos);
        swRepetirNumeral = (Switch)findViewById(R.id.swRepetirNumeral);
        btSalvar = (Button)findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etNumDigitos.getText().toString().isEmpty()){
                    if(Integer.parseInt(etNumDigitos.getText().toString()) > 9 || Integer.parseInt(etNumDigitos.getText().toString()) < 1){
                        etNumDigitos.setError("É necessário ser maior do que 0(Zero) e menor do que 10(Dez)");
                    } else {
                        Menu.configuracao.setRepetirNum(swRepetirNumeral.isChecked());
                        Menu.configuracao.setNumDigitos(Integer.parseInt(etNumDigitos.getText().toString()));
                        Toast.makeText(Configuracoes.this, "Salvo com Sucesso!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    etNumDigitos.setError("Não pode ser vazio!");
                }
            }
        });

        etNumDigitos.setText(""+Menu.configuracao.getNumDigitos());
        swRepetirNumeral.setChecked(Menu.configuracao.isRepetirNum());

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Menu.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home){
            // Start the Signup activity
            Intent i = new Intent(getApplicationContext(), Menu.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
