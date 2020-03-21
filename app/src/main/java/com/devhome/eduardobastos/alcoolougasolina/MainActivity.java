package com.devhome.eduardobastos.alcoolougasolina;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {


    private EditText PrecoAlcool;
    private EditText PrecoGasolina;
    private LinearLayout linearLayout;
    private AdView bannerAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-8069899346555474/8336523279");

        bannerAd = findViewById(R.id.bannerAd);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAd.loadAd(adRequest);



        linearLayout = findViewById(R.id.linearLayout);
        PrecoAlcool = findViewById(R.id.PrecoAlcool);
        PrecoGasolina = findViewById(R.id.PrecoGasolina); // Referencias para os componentes na tela



// VERIFICA SE OS CAMPOS ESTAO VAZIOS

        //Ocultar teclado

        PrecoAlcool.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                if (!b) {
                    escondeTeclado(v);
                }
            }
        });


        PrecoGasolina.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View vv, boolean bb) {
                if (!bb) {
                    escondeTeclado(vv);
                }
            }
        });

    }
    public void escondeTeclado(View v) {

        if (v != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }




        public void calcularPreco(View view){
        // RECUPERAR VALORES DIGITADOS

           String valorAlcool = PrecoAlcool.getText().toString();      // O getText() recupera o texto e converte para uma String  .toString()
            String valorGasolina = PrecoGasolina.getText().toString();

            //VALIDAR OS CAMPOS DIGITADOS



            Boolean camposValidados = this.validarCampos(valorAlcool, valorGasolina);

            if ( camposValidados ){

                    this.MelhorCxB(valorAlcool, valorGasolina);
            }else{

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Aviso!");
        dlg.setMessage("Há campos vazios.");
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

    escondeTeclado(view);

    }

         public void MelhorCxB(String pAlcool, String pGasolina){

        //CONVERTER VALORES STRING PARA NUMEROS

             Double valorAlcool = Double.parseDouble(pAlcool);
             Double valorGasolina = Double.parseDouble(pGasolina);

             /* Faz cálculo (valorAlcool / valorGasolina)
             * Se resultado >= 0.7 melhor utilizar gasolina
             * Senao melhor abastecer com Alcool
             * */

             Double resultado = valorAlcool / valorGasolina;

             if (resultado >= 0.7){

                 AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                 dlg.setTitle("Resultado:");
                 dlg.setMessage("O valor do Álcool representa menos de 70% do valor " +
                         "da Gasolina logo, melhor abastecer com Gasolina!");
                 dlg.setNeutralButton("Fechar", null);
                 dlg.show();
                 //textViewResult.setText("Melhor abastecer com Gasolina.");
             }else{

                 AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                 dlg.setTitle("Resultado:");
                 dlg.setMessage("Nesse caso, o valor do Álcool é maior ou igual" +
                         " a 70% o valor da Gasolina logo, melhor abastecer com Álcool!");
                 dlg.setNeutralButton("Fechar", null);
                 dlg.show();
                 //textViewResult.setText("Melhor abastecer com Álcool.");
             }


         }

    //VALIDAR OS CAMPOS DIGITADOS!!!

         public Boolean validarCampos (String pAlcool, String pGasolina){


                Boolean camposValidados = true;

          //Validar Campos

             if (pAlcool == null || pAlcool.equals("")){

                 camposValidados = false;

             }else if (pGasolina == null || pGasolina.equals("")){

                 camposValidados = false;

             }
             return camposValidados;


         }

         //Limpar Campos
         public void limpar(View view){
             PrecoAlcool.setText("");
            PrecoGasolina.setText("");


            linearLayout.requestFocus(View.KEEP_SCREEN_ON);

             Toast.makeText(getApplicationContext(), "Campos Apagados!",
                     Toast.LENGTH_LONG).show();

         }


}