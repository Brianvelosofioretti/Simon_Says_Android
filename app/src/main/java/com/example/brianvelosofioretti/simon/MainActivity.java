package com.example.brianvelosofioretti.simon;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // llama al content main
        //hacemos las referencias de los botones
        boton1 = (Button) findViewById(R.id.button1);
        boton2 = (Button) findViewById(R.id.button2);
        boton3 = (Button) findViewById(R.id.button3);
        boton4 = (Button) findViewById(R.id.button4);
        boton5 = (Button) findViewById(R.id.button5);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    MediaPlayer mp,mp1,mp2,mp3,mp4;//sonidos



    int contador=1;
    int nivel=0;

    Button boton1, boton2, boton3, boton4,boton5;
    Handler handler = new Handler();//hilo 1
    Handler handler2 = new Handler();//hilo 2
    ArrayList<Integer>genera_juego=new ArrayList<>(4); //array para el juego
    ArrayList<Integer>pulsa_jugador=new ArrayList<>(4);// array del jugador

    int time = 1000;



    // e comprueba con el aaraylist del jugador y el generado por el juego
    public void comprobar(){
        mp=MediaPlayer.create(this,R.raw.error_dog);
        final Toast txtlooser = Toast.makeText(getApplicationContext(),"Perdiste", Toast.LENGTH_SHORT);

        if (genera_juego.size() == pulsa_jugador.size()) {//si el tamaño de los dos es igual
            for (int i = 0; i < genera_juego.size(); i++) {//recorro el array
                if (genera_juego.get(i).equals(pulsa_jugador.get(i))) {//compruebo que sean iguales
                    visualizar();
                } else {
                    mp.start();
                    genera_juego.clear();//borra arrays
                    pulsa_jugador.clear();
                    txtlooser.show(); //si fallas muestra mensaje
                    nivel=0;//reinicia el nivel

                }

            }



        }
    }

    public void pulsaciones() {//pulsacion de cada boton en cada uno se añade un dato al array
        mp1=MediaPlayer.create(this,R.raw.sol);
        mp2=MediaPlayer.create(this,R.raw.re);
        mp3=MediaPlayer.create(this,R.raw.mi);
        mp4=MediaPlayer.create(this,R.raw.fa);



        //cada vez que pulsa el boton comprueba el tamaño del array
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //ilumino la pulsacion
                boton1.setBackgroundColor(Color.parseColor("#FFEA4D39"));
                pausa();//apago el boton
                pulsa_jugador.add(1);//añado al array
                comprobar(); //comprobamos posicion

                mp1.start();// reproduce sonido

            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton2.setBackgroundColor(Color.parseColor("#FF73FF0E"));
                pausa();
                pulsa_jugador.add(2);
                comprobar();
                mp2.start();


            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton3.setBackgroundColor(Color.parseColor("#FFF2D200"));
                pausa();
                pulsa_jugador.add(3);
                comprobar();
                mp3.start();

            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton4.setBackgroundColor(Color.parseColor("#FF02AAFF"));
                pausa();
                pulsa_jugador.add(4);
                comprobar();
                mp4.start();


            }
        });


    }


    public void pausa() {

            handler.postDelayed(new Runnable() {//apagamos los botones
                @Override
                public void run() {
                    boton1.setBackgroundColor(Color.parseColor("#FF560A00"));
                    boton2.setBackgroundColor(Color.parseColor("#FF005000"));
                    boton3.setBackgroundColor(Color.parseColor("#FF705B00"));
                    boton4.setBackgroundColor(Color.parseColor("#FF00476B"));


                }
            },  150);// espera a apagar y apaga
        }
   // }
    public void jugar(View jugar) throws InterruptedException {//siempre debemos pasar un view para que lo reconozca en el content
        nivel++;
        juego(nivel);//aumentamos el nivel segun vayamos avanzando

       boton5.setText(" ");





    }

    public void visualizar() {
        //visualizamos la puntuacion en el boton de inicio
        TextView txtPuntos = (TextView) findViewById(R.id.button5);
        txtPuntos.setText("Nivel: " +nivel+" superado  \n Pulsa de nuevo \n cuando estes listo");

    }


    public void juego(int nivel) throws InterruptedException {// ejecucion del juego
        this.nivel=nivel;


        for (int i = 0; i < nivel; i++) {//incrementa dificultad

            handler2.postDelayed(new Runnable() {
                @Override

                public void run() {

                    int random = (int) Math.floor(Math.random() * 4 + 1);

                    if (random == 1) {

                        genera_juego.add(1);
                        boton1.setBackgroundColor(Color.parseColor("#FFEA4D39"));


                        pausa();

                        mp1.start();



                    } else if (random == 2) {

                        genera_juego.add(2);
                        boton2.setBackgroundColor(Color.parseColor("#FF73FF0E"));
                        pausa();
                        mp2.start();




                    } else if (random == 3) {
                        genera_juego.add(3);
                        boton3.setBackgroundColor(Color.parseColor("#FFF2D200"));
                        pausa();
                        mp3.start();




                    } else if (random == 4) {

                        genera_juego.add(4);
                        boton4.setBackgroundColor(Color.parseColor("#FF02AAFF"));
                        pausa();
                        mp4.start();





                    }


                }
            }, time * i + 30); //aumenta el tiempo de espera por cada boton e ilumina


        }
        pulsaciones();// llamamos a las pulsaciones del jugador para proceder a la comparacion

    }


}



