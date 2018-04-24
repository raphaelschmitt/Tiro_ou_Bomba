package com.schmitt.tiro_ou_bomba;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Jogo extends AppCompatActivity {

    private TextView tvNomeJogador;
    private EditText etPalpite;
    private Button btPalpitar, btDesistir, btPassarVez;
    private ListView listView;

    int vezJogador = 1;
    ArrayList<String> todosPalpites;
    ArrayAdapter<String> adapter;

    int palpites = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        tvNomeJogador = (TextView)findViewById(R.id.tvNomeJogador);
        etPalpite = (EditText)findViewById(R.id.etPalpite);
        btPalpitar = (Button)findViewById(R.id.btPalpitar);
        btDesistir = (Button)findViewById(R.id.btDesistir);
        btPassarVez = (Button)findViewById(R.id.btPassarVez);
        listView = (ListView)findViewById(R.id.lista);

        btPalpitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard();
                if(palpites == 0){
                    AlertDialog.Builder mesg = new AlertDialog.Builder(Jogo.this);
                    mesg.setMessage("Só é possível efetuar um palpite por turno!");
                    mesg.setTitle("Atenção!");
                    mesg.setNeutralButton("Ok", null);
                    mesg.setIcon(android.R.drawable.ic_dialog_alert);
                    mesg.show();
                } else {
                    palpites--;
                    Palpitar();
                }
            }
        });

        btDesistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideSoftKeyboard();

                AlertDialog.Builder mesg = new AlertDialog.Builder(Jogo.this);
                mesg.setMessage("Tem certeza que deseja desistir e voltar para o Menu?");
                mesg.setTitle("");
                mesg.setCancelable(true);
                mesg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), Menu.class);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        finish();
                    }
                });
                mesg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mesg.show();
            }
        });

        btPassarVez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard();
                if(palpites == 0){
                    palpites++;
                    if(vezJogador == 1){
                        tvNomeJogador.setText(Jogar_pvp.jogador2.getNomeJogador());

                        todosPalpites = Jogar_pvp.jogador2.getPalpitesAnteriores();

                        if(todosPalpites.size() <= 0){
                            listView.setVisibility(View.GONE);
                        } else {
                            listView.setVisibility(View.VISIBLE);
                        }

                        adapter = new ArrayAdapter<String>(Jogo.this, android.R.layout.simple_list_item_1, todosPalpites);

                        listView.setAdapter(adapter);

                        vezJogador = 2;
                    } else if(vezJogador == 2) {
                        tvNomeJogador.setText(Jogar_pvp.jogador1.getNomeJogador());

                        todosPalpites = Jogar_pvp.jogador1.getPalpitesAnteriores();

                        if(todosPalpites.size() <= 0){
                            listView.setVisibility(View.GONE);
                        } else {
                            listView.setVisibility(View.VISIBLE);
                        }

                        adapter = new ArrayAdapter<String>(Jogo.this, android.R.layout.simple_list_item_1, todosPalpites);

                        listView.setAdapter(adapter);

                        vezJogador = 1;
                    }
                } else {
                    AlertDialog.Builder mesg = new AlertDialog.Builder(Jogo.this);
                    mesg.setMessage("É necessário realizar um palpite antes de passar a vez!");
                    mesg.setTitle("Atenção!");
                    mesg.setNeutralButton("Ok", null);
                    mesg.setIcon(android.R.drawable.ic_dialog_alert);
                    mesg.show();
                }

            }
        });

        tvNomeJogador.setText(Jogar_pvp.jogador1.getNomeJogador());

        todosPalpites = Jogar_pvp.jogador1.getPalpitesAnteriores();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todosPalpites);

        listView.setAdapter(adapter);

        if(todosPalpites.size() <= 0){
            listView.setVisibility(View.GONE);
        } else {
            listView.setVisibility(View.VISIBLE);
        }
    }

    public void Palpitar(){
        if(!etPalpite.getText().toString().isEmpty()){
            if(etPalpite.getText().toString().length() == Menu.configuracao.getNumDigitos()){
                if(vezJogador == 1){
                    String resultado = VerificaPalpite(etPalpite.getText().toString());

                    if(resultado.equals("ACERTOU")){

                        AlertDialog.Builder mesg = new AlertDialog.Builder(Jogo.this);
                        mesg.setMessage("Parabéns! Você acertou!" +
                                "\nVencedor: " + Jogar_pvp.jogador1.getNomeJogador() +
                                "\nCódigo do Vencedor: " + Jogar_pvp.jogador1.getNumJogador() +
                                "\nCódigo do Adversário: " + Jogar_pvp.jogador2.getNumJogador());
                        mesg.setTitle("");
                        mesg.setCancelable(true);
                        mesg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), Menu.class);
                                startActivity(i);
                                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                finish();
                            }
                        });
                        mesg.show();

                    } else {

                        Jogar_pvp.jogador1.getPalpitesAnteriores().add(resultado);

                        todosPalpites = Jogar_pvp.jogador1.getPalpitesAnteriores();

                        if(todosPalpites.size() <= 0){
                            listView.setVisibility(View.GONE);
                        } else {
                            listView.setVisibility(View.VISIBLE);
                        }

                        adapter = new ArrayAdapter<String>(Jogo.this, android.R.layout.simple_list_item_1, todosPalpites);

                        listView.setAdapter(adapter);

                        AlertDialog.Builder mesg = new AlertDialog.Builder(Jogo.this);
                        mesg.setMessage("Resultado do Palpite: " + resultado);
                        mesg.setTitle("Não foi dessa vez!");
                        mesg.setNeutralButton("Ok", null);
                        mesg.setIcon(android.R.drawable.ic_dialog_alert);
                        mesg.show();

                    }
                } else if(vezJogador == 2){
                    String resultado = VerificaPalpite(etPalpite.getText().toString());

                    if(resultado.equals("ACERTOU")){

                        AlertDialog.Builder mesg = new AlertDialog.Builder(Jogo.this);
                        mesg.setMessage("Parabéns! Você acertou!" +
                                "\nVencedor: " + Jogar_pvp.jogador2.getNomeJogador() +
                                "\nCódigo do Vencedor: " + Jogar_pvp.jogador2.getNumJogador() +
                                "\nCódigo do Adversário: " + Jogar_pvp.jogador1.getNumJogador());
                        mesg.setTitle("");
                        mesg.setCancelable(true);
                        mesg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), Menu.class);
                                startActivity(i);
                                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                finish();
                            }
                        });
                        mesg.show();

                    } else {

                        Jogar_pvp.jogador2.getPalpitesAnteriores().add(resultado);

                        todosPalpites = Jogar_pvp.jogador2.getPalpitesAnteriores();

                        if(todosPalpites.size() <= 0){
                            listView.setVisibility(View.GONE);
                        } else {
                            listView.setVisibility(View.VISIBLE);
                        }

                        adapter = new ArrayAdapter<String>(Jogo.this, android.R.layout.simple_list_item_1, todosPalpites);

                        listView.setAdapter(adapter);

                        AlertDialog.Builder mesg = new AlertDialog.Builder(Jogo.this);
                        mesg.setMessage("Resultado do Palpite: " + resultado);
                        mesg.setTitle("Não foi dessa vez!");
                        mesg.setNeutralButton("Ok", null);
                        mesg.setIcon(android.R.drawable.ic_dialog_alert);
                        mesg.show();

                    }
                }
                etPalpite.setText("");
            } else {
                etPalpite.setError("O palpite tem que ter " + Menu.configuracao.getNumDigitos() + " dígitos.");
                palpites++;
            }
        } else {
            etPalpite.setError("Não pode ser vazio!");
            palpites++;
        }
    }

    public String VerificaPalpite(String palpite){

        int numBombas = 0, numTiros = 0;

        if(vezJogador == 2){
            for(int i = 0; i < Menu.configuracao.getNumDigitos(); i++){
                if(palpite.charAt(i) == Jogar_pvp.jogador1.getNumJogador().charAt(i)){
                    numBombas++;
                }

                for(int j = 0; j < Menu.configuracao.getNumDigitos(); j++){
                    if(palpite.charAt(i) == Jogar_pvp.jogador1.getNumJogador().charAt(j)){
                        numTiros++;
                    }
                }
            }
        } else {
            for(int i = 0; i < Menu.configuracao.getNumDigitos(); i++){
                if(palpite.charAt(i) == Jogar_pvp.jogador2.getNumJogador().charAt(i)){
                    numBombas++;
                }

                for(int j = 0; j < Menu.configuracao.getNumDigitos(); j++){
                    if(palpite.charAt(i) == Jogar_pvp.jogador2.getNumJogador().charAt(j)){
                        numTiros++;
                    }
                }
            }
        }

        numTiros = numTiros - numBombas;

        if(numBombas == Menu.configuracao.getNumDigitos()) {
            return "ACERTOU";
        }
        return palpite + " -> Tiros = " + numTiros + " | Bombas = " + numBombas;
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
