package com.example.proyectojoseparejaarrebola;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectojoseparejaarrebola.databinding.ActivityMainBinding;
import com.example.proyectojoseparejaarrebola.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

/**
 * Clase principal de la aplicación que gestiona la actividad principal (MainActivity).
 * Se encarga de configurar el menú de navegación lateral (DrawerLayout), las opciones
 * de idioma, el diálogo "Acerca de" y la navegación entre fragmentos.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;

    /**
     * Método llamado cuando se crea la actividad.
     * Configura el DrawerLayout, el NavigationView y el controlador de navegación
     * para gestionar la interacción entre los elementos de la interfaz.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Usamos el binding para inicializar la vista
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el Toolbar usando el binding
        setSupportActionBar(binding.appBarMain.toolbar);

        // Configura el DrawerLayout y NavigationView
        drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Configura la navegación con AppBarConfiguration
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawerLayout)
                .build();

        // Configura el NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Establece el listener para el NavigationView
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Método para inflar el menú de opciones.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Método para gestionar la navegación hacia arriba.
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Muestra un diálogo "Acerca de" con información de la aplicación.
     */
    private void mostrarDialogo() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.acerca_de))
                .setMessage(getString(R.string.desarrollador))
                .setIcon(R.drawable.iconacercade) // Icono de "acerca de"
                .setPositiveButton("OK", null) // Botón para cerrar
                .show();
    }

    /**
     * Maneja las acciones seleccionadas en el menú de opciones.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_acerca_de) {
            mostrarDialogo(); // Mostrar el diálogo "Acerca de"
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Gestiona las acciones seleccionadas en el menú de navegación lateral.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Reemplaza el fragmento con la lista de personajes de Super Mario
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new HomeFragment())
                    .commit();
        } else if (id == R.id.nav_ajustes) {
            // Reemplaza con el fragmento de ajustes
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new SettingsFragment())
                    .commit();
        } else if (id == R.id.nav_idioma) {
            // Cambia el idioma
            showLanguageDialog();
        }

        drawerLayout.closeDrawer(GravityCompat.START);  // Cierra el menú lateral
        return true;
    }

    /**
     * Muestra un diálogo para cambiar el idioma de la aplicación.
     */
    private void showLanguageDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.idioma)
                .setItems(new String[]{"English", "Español"}, (dialog, which) -> {
                    if (which == 0) {
                        changeLanguage("en"); // Cambiar a inglés
                    } else {
                        changeLanguage("es"); // Cambiar a español
                    }
                })
                .show();
    }

    /**
     * Cambia el idioma de la aplicación y reinicia la actividad para aplicar el cambio.
     */
    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Reinicia la actividad para aplicar el cambio
        recreate();
    }

    /**
     * Maneja el comportamiento del botón "Atrás".
     * Cierra el Drawer si está abierto, o realiza la acción predeterminada.
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}