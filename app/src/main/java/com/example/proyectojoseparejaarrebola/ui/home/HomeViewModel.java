package com.example.proyectojoseparejaarrebola.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * ViewModel para gestionar la lista de personajes en la interfaz de usuario.
 * Proporciona la lógica para almacenar y recuperar los personajes.
 */
public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Personaje>> personajes;

    /**
     * Constructor del ViewModel, inicializa la lista de personajes.
     */
    public HomeViewModel() {
        personajes = new MutableLiveData<>();
    }

    /**
     * Actualiza la lista de personajes.
     */
    public void setPersonajes(List<Personaje> personajesList) {
        personajes.setValue(personajesList);
    }

    /**
     * Obtiene la lista de personajes como un LiveData.
     * @return LiveData que contiene la lista de personajes.
     */
    public LiveData<List<Personaje>> getPersonajes() {
        return personajes;
    }

    /**
     * Clase interna que representa un personaje con su nombre, imagen, descripción y habilidades.
     */
    public static class Personaje {
        private String nombre;
        private int imagenId;
        private String descripcion;
        private String habilidades;

        /**
         * Constructor para crear un personaje con los datos proporcionados.
         */
        public Personaje(String nombre, int imagenId, String descripcion, String habilidades) {
            this.nombre = nombre;
            this.imagenId = imagenId;
            this.descripcion = descripcion;
            this.habilidades = habilidades;
        }

        /**
         * Obtiene el nombre del personaje.
         *
         * @return Nombre del personaje.
         */
        public String obtenerNombre() {
            return nombre;
        }

        /**
         * Obtiene el ID de la imagen del personaje.
         *
         * @return ID de la imagen del personaje.
         */
        public int obtenerImagenId() {
            return imagenId;
        }

        /**
         * Obtiene la descripción del personaje.
         *
         * @return Descripción del personaje.
         */
        public String obtenerDescripcion() {
            return descripcion;
        }

        /**
         * Obtiene las habilidades del personaje.
         *
         * @return Habilidades del personaje.
         */
        public String obtenerHabilidades() {
            return habilidades;
        }
    }
}