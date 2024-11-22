package com.example.proyectojoseparejaarrebola;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase representa la pantalla de bienvenida (Splash Screen) de la aplicación donde aparece
 * la cara de Mario Bros.
 * La pantalla se muestra durante 3 segundos antes de navegar automáticamente
 * a la actividad principal (MainActivity).
 * Utiliza un temporizador (Timer) para gestionar el tiempo de espera y la transición.
 */
public class PantallaSplash extends AppCompatActivity {
    /**
     * Método llamado cuando se crea la actividad.
     * Establece el diseño de la pantalla de bienvenida
     * y programa una tarea que cambia a la actividad principal después de 3 segundos.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_splash);

        // Usamos un Timer para esperar 3 segundos antes de cambiar a la siguiente actividad
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            /**
             * Tarea ejecutada después de que el temporizador cumpla el tiempo especificado.
             * Cambia a la actividad principal y cierra la actividad de bienvenida.
             */
            @Override
            public void run() {
                // Después de 3 segundos, abrimos MainActivity
                Intent intent = new Intent(PantallaSplash.this, MainActivity.class);
                startActivity(intent); // Inicia la nueva actividad
                finish(); // Termina esta actividad para que no se regrese a la pantalla de Splash
            }
        }, 3000); // 3000 milisegundos = 3 segundos
    }
}
