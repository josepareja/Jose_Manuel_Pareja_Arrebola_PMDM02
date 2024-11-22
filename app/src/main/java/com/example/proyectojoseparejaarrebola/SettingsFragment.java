package com.example.proyectojoseparejaarrebola;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

/**
 * Este fragmento se encacrga de cambiar el idioma de la interfaz entre español e inglés.
 * Incluye un interruptor (Switch) que permite al usuario seleccionar el idioma
 * deseado, guardando la preferencia de forma persistente mediante SharedPreferences.
 * Cuando el usuario cambia el idioma, la interfaz se actualiza automáticamente.
 */
public class SettingsFragment extends Fragment {
    /**
     * Switch para seleccionar el idioma de la aplicación.
     * Si está activado, la aplicación usará inglés; si está desactivado, español.
     */

    private Switch idiomaSwitch;

    /**
     * Crea y devuelve la vista asociada a este fragmento.
     * Aquí se inicializan los componentes de la interfaz y se cargan las preferencias de idioma.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        // Inicializar el Switch de idioma
        idiomaSwitch = rootView.findViewById(R.id.nav_idioma);

        // Cargar preferencia de idioma desde SharedPreferences
        SharedPreferences preferences = getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        boolean isEnglish = preferences.getBoolean("is_english", false);
        idiomaSwitch.setChecked(isEnglish);

        // Configurar el listener para cambiar el idioma
        idiomaSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            changeLanguage(isChecked ? "en" : "es");
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("is_english", isChecked);
            editor.apply();
        });

        return rootView;
    }

    /**
     * Cambia el idioma de la aplicación y actualiza la configuración.
     */
    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        getActivity().recreate();
    }
}
