package com.example.proyectojoseparejaarrebola;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * Fragmento que muestra las características detalladas de un personaje.
 * Este fragmento recibe datos a través de argumentos y los muestra en la interfaz de usuario.
 */
public class CaracteristicasPersonajeFragment extends Fragment {

    /**
     * Constructor vacío del fragmento.
     */
    public CaracteristicasPersonajeFragment() {
    }

    /**
     * Este método es llamado cuando se crea la vista del fragmento.
     * Se infla el diseño y se configura la vista de acuerdo a los datos recibidos.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View rootView = inflater.inflate(R.layout.personaje_detalle, container, false);

        // Inicializar el botón de atrás
        Button atrasButton = rootView.findViewById(R.id.btn_atras);

        // Configura el listener para el botón "Atrás"
        atrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llama a requireActivity().onBackPressed() para volver atrás
                requireActivity().onBackPressed();
            }
        });

        // Recibir los datos a través de los argumentos
        Bundle args = getArguments();
        String nombre = args != null ? args.getString("nombre") : "";
        String descripcion = args != null ? args.getString("descripcion") : "";
        String habilidades = args != null ? args.getString("habilidades") : "";
        int imagenId = args != null ? args.getInt("imagenId", -1) : -1;

        // Inicializar las vistas
        TextView nombreTextView = rootView.findViewById(R.id.caracteristicas_nombre);
        TextView descripcionTextView = rootView.findViewById(R.id.caracteristicas_descripcion);
        TextView habilidadesTextView = rootView.findViewById(R.id.caracteristicas_personaje);
        ImageView imagenImageView = rootView.findViewById(R.id.caracteristicas_imagen);

        // Asignar los valores recibidos
        nombreTextView.setText(nombre);
        descripcionTextView.setText(descripcion);
        habilidadesTextView.setText(habilidades);
        if (imagenId != -1) {
            imagenImageView.setImageResource(imagenId);
        }

        // Mostrar un Toast con el nombre del personaje
        Toast.makeText(getContext(), getString(R.string.toastMensaje, nombre), Toast.LENGTH_SHORT).show();

        return rootView;
    }

    /**
     * Crea una nueva instancia del fragmento con los datos proporcionados.
     * @return Una nueva instancia del fragmento con los datos proporcionados.
     */
    public static CaracteristicasPersonajeFragment newInstance(String nombre, String descripcion, String habilidades, int imagenId) {
        CaracteristicasPersonajeFragment fragment = new CaracteristicasPersonajeFragment();
        Bundle args = new Bundle();
        args.putString("nombre", nombre);
        args.putString("descripcion", descripcion);
        args.putString("habilidades", habilidades);
        args.putInt("imagenId", imagenId);
        fragment.setArguments(args);
        return fragment;
    }
}