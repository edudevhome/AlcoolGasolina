package com.devhome.eduardobastos.alcoolougasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText PrecoAlcool;
    private EditText PrecoGasolina;
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrecoAlcool = findViewById(R.id.PrecoAlcool);
        PrecoGasolina = findViewById(R.id.PrecoGasolina); // Referencias para os componentes na tela
        textViewResult = findViewById(R.id.textViewResult);


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

                textViewResult.setText("Preencha os valores corretamente.");
            }




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
                 textViewResult.setText("Melhor abastecer com Gasolina.");
             }else{
                 textViewResult.setText("Melhor abastecer com Álcool.");
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


}
