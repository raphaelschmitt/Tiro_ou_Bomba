package com.schmitt.tiro_ou_bomba;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.schmitt.tiro_ou_bomba.Objetos.Jogador;

import java.util.ArrayList;

public class Jogar_pvp extends AppCompatActivity {

    private Toolbar mToolbar;

    private EditText etNomeJogador1, etNomeJogador2, etNumJogador1, etNumJogador2;
    private Switch swNumJog1, swNumJog2;
    private Button btInciar;

    public static Jogador jogador1 = new Jogador("", "", new ArrayList<String>()),
            jogador2 = new Jogador("", "", new ArrayList<String>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar_pvp);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Jogador vs Jogador");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNomeJogador1 = (EditText)findViewById(R.id.etNomeJogador1);
        etNomeJogador2 = (EditText)findViewById(R.id.etNomeJogador2);
        etNumJogador1 = (EditText)findViewById(R.id.etNumJogador1);
        etNumJogador2 = (EditText)findViewById(R.id.etNumJogador2);
        swNumJog1 = (Switch)findViewById(R.id.swNumJog1);
        swNumJog2 = (Switch)findViewById(R.id.swNumJog2);
        btInciar = (Button)findViewById(R.id.btIniciar);

        swNumJog1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                hideSoftKeyboard();

                if(isChecked){
                    etNumJogador1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etNumJogador1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        swNumJog2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                hideSoftKeyboard();

                if(isChecked){
                    etNumJogador2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etNumJogador2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btInciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideSoftKeyboard();

                if(etNomeJogador1.getText().toString().isEmpty() || etNomeJogador2.getText().toString().isEmpty()
                        || etNumJogador1.getText().toString().isEmpty() || etNumJogador2.getText().toString().isEmpty()){

                    AlertDialog.Builder mesg = new AlertDialog.Builder(Jogar_pvp.this);
                    mesg.setMessage("Todos os campos precisam ser preenchidos.");
                    mesg.setTitle("Atenção");
                    mesg.setNeutralButton("Ok", null);
                    mesg.setIcon(android.R.drawable.ic_dialog_alert);
                    mesg.show();

                } else if(etNumJogador1.getText().toString().length() != Menu.configuracao.getNumDigitos()){

                    etNumJogador1.setError("É necessário ter "+Menu.configuracao.getNumDigitos()+" dígitos em seu código.");

                } else if(etNumJogador2.getText().toString().length() != Menu.configuracao.getNumDigitos()){

                    etNumJogador2.setError("É necessário ter "+Menu.configuracao.getNumDigitos()+" dígitos em seu código.");

                } else if(!Menu.configuracao.isRepetirNum() && possuiIgual(etNumJogador1.getText().toString())){

                    etNumJogador1.setError("Não pode possuir dígitos repetidos.");

                }  else if(!Menu.configuracao.isRepetirNum() && possuiIgual(etNumJogador2.getText().toString())){

                    etNumJogador2.setError("Não pode possuir dígitos repetidos.");

                } else if(!swNumJog1.isChecked()){

                    swNumJog1.setError("É necessário esconder o seu código!");

                } else if(!swNumJog2.isChecked()){

                    swNumJog2.setError("É necessário esconder o seu código!");

                } else {

                    jogador1.setNomeJogador(etNomeJogador1.getText().toString());
                    jogador1.setNumJogador(etNumJogador1.getText().toString());
                    jogador1.setPalpitesAnteriores(new ArrayList<String>());

                    jogador2.setNomeJogador(etNomeJogador2.getText().toString());
                    jogador2.setNumJogador(etNumJogador2.getText().toString());
                    jogador2.setPalpitesAnteriores(new ArrayList<String>());

                    Intent i = new Intent(getApplicationContext(), Jogo.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();

                }
            }
        });

    }

    public boolean possuiIgual(String numero){
        for(int i = 0; i < numero.length(); i++){
            for(int j = 0; j < numero.length(); j++){
                if(i != j){
                    if(numero.charAt(i) == numero.charAt(j)){
                        return true;
                    }
                }
            }
        }
        return false;
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

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
