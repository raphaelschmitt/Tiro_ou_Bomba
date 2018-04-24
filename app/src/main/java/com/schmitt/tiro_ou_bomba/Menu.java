package com.schmitt.tiro_ou_bomba;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.schmitt.tiro_ou_bomba.Objetos.Configuracao;

public class Menu extends AppCompatActivity {

    Button btJogar_pvp, btJogar_pve, btConfig, btSair;

    int countBackPressed = 0;

    public static Configuracao configuracao = new Configuracao(4,9,false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btJogar_pvp = (Button)findViewById(R.id.btJogar_pvp);
        btJogar_pve = (Button)findViewById(R.id.btJogar_pve);
        btConfig = (Button)findViewById(R.id.btConfig);
        btSair = (Button)findViewById(R.id.btSair);

        btJogar_pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Jogar_pvp.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        btJogar_pve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Configuracoes.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mesg = new AlertDialog.Builder(Menu.this);
                mesg.setMessage("Deseja fechar o aplicativo?");
                mesg.setTitle("");
                mesg.setCancelable(true);
                mesg.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                mesg.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        countBackPressed = 0;
                        return;
                    }
                });
                mesg.show();
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(countBackPressed >= 1){
            AlertDialog.Builder mesg = new AlertDialog.Builder(Menu.this);
            mesg.setMessage("Deseja fechar o aplicativo?");
            mesg.setTitle("");
            mesg.setCancelable(true);
            mesg.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            mesg.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    countBackPressed = 0;
                    return;
                }
            });
            mesg.show();
        }

        countBackPressed++;
    }

}
