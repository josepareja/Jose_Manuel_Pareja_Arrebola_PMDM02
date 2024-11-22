package com.example.proyectojoseparejaarrebola.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;

import com.example.proyectojoseparejaarrebola.databinding.FragmentHomeBinding;
import com.example.proyectojoseparejaarrebola.R;
import com.example.proyectojoseparejaarrebola.CaracteristicasPersonajeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragmento que muestra una lista de personajes utilizando un RecyclerView.
 * Cada personaje se puede seleccionar para ver más detalles en un fragmento separado.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    /**
     * Método que se llama al crear la vista del fragmento.
     * Aquí se configura el RecyclerView y se observa el ViewModel para actualizar la lista de personajes.
     * @return La vista inflada del fragmento.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inicializar el ViewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Inflar el layout
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configurar el RecyclerView
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Llamamos a la función para llenar la lista de personajes con las traducciones
        rellenarRecyclerView();

        // Observar los datos del ViewModel
        homeViewModel.getPersonajes().observe(getViewLifecycleOwner(), personajes -> {
            // Configurar el adaptador cuando se obtienen los personajes
            RVAdapter adapter = new RVAdapter(personajes, personaje -> {
                // Crear una nueva instancia de CaracteristicasPersonajeFragment con los datos del personaje
                CaracteristicasPersonajeFragment fragment = CaracteristicasPersonajeFragment.newInstance(
                        personaje.obtenerNombre(),
                        personaje.obtenerDescripcion(),
                        personaje.obtenerHabilidades(),
                        personaje.obtenerImagenId()
                );

                // Reemplazar el fragmento actual con CaracteristicasPersonajeFragment
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, fragment)
                        .addToBackStack(null) // Permite volver al HomeFragment al presionar "Atrás"
                        .commit();
            });

            recyclerView.setAdapter(adapter);
        });

        return root;
    }

    /**
     * Método que llena el RecyclerView con una lista de personajes.
     * Los personajes incluyen traducciones de texto como nombre, descripción y habilidades.
     */
    private void rellenarRecyclerView() {
        // Crear la lista de personajes con los textos traducidos
        List<HomeViewModel.Personaje> personajesList = new ArrayList<>();
        personajesList.add(new HomeViewModel.Personaje(
                getString(R.string.mario),
                R.drawable.mario,
                getString(R.string.descripcion_mario),
                getString(R.string.habilidades_mario)
        ));
        personajesList.add(new HomeViewModel.Personaje(
                getString(R.string.luigi),
                R.drawable.luigi,
                getString(R.string.descripcion_luigi),
                getString(R.string.habilidades_luigi)
        ));
        personajesList.add(new HomeViewModel.Personaje(
                getString(R.string.peach),
                R.drawable.peach,
                getString(R.string.descripcion_peach),
                getString(R.string.habilidades_peach)
        ));
        personajesList.add(new HomeViewModel.Personaje(
                getString(R.string.toad),
                R.drawable.toad,
                getString(R.string.descripcion_toad),
                getString(R.string.habilidades_toad)
        ));

        // Actualiza el LiveData con la lista de personajes
        homeViewModel.setPersonajes(personajesList);
    }

    /**
     * Método que limpia el binding cuando la vista del fragmento es destruida.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Adaptador para el RecyclerView que muestra los personajes.
     * Permite hacer clic en un personaje para ver más detalles.
     */
    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonajeViewHolder> {

        private List<HomeViewModel.Personaje> personajes;
        private OnItemClickListener clickListener;

        /**
         * Interfaz para manejar el clic en un personaje de la lista.
         */
        public interface OnItemClickListener {
            void onItemClick(HomeViewModel.Personaje personaje);
        }

        /**
         * Constructor del adaptador que recibe la lista de personajes y el listener de clics.
         */
        public RVAdapter(List<HomeViewModel.Personaje> personajes, OnItemClickListener clickListener) {
            this.personajes = personajes;
            this.clickListener = clickListener;
        }

        /**
         * Método llamado para crear un nuevo ViewHolder.
         * @return Un nuevo ViewHolder para el personaje.
         */
        @Override
        public PersonajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
            return new PersonajeViewHolder(v);
        }

        /**
         * Método llamado para enlazar los datos del personaje a la vista.
         */
        @Override
        public void onBindViewHolder(PersonajeViewHolder holder, int position) {
            HomeViewModel.Personaje personaje = personajes.get(position);
            holder.personajeNombre.setText(personaje.obtenerNombre());
            holder.personajeFoto.setImageResource(personaje.obtenerImagenId());
        }

        /**
         * Método que devuelve el número de elementos en la lista de personajes.
         * @return El número de personajes.
         */
        @Override
        public int getItemCount() {
            return personajes.size();
        }

        /**
         * ViewHolder para los elementos de la lista de personajes.
         * Contiene las vistas que representan un personaje.
         */
        public class PersonajeViewHolder extends RecyclerView.ViewHolder {

            public TextView personajeNombre;
            public ImageView personajeFoto;

            /**
             * Constructor que inicializa las vistas del ViewHolder.
             */
            public PersonajeViewHolder(View itemView) {
                super(itemView);
                personajeNombre = itemView.findViewById(R.id.personaje_nombre);
                personajeFoto = itemView.findViewById(R.id.imagen_personaje);

                // Configurar clic en el elemento
                itemView.setOnClickListener(v -> clickListener.onItemClick(personajes.get(getAdapterPosition())));
            }
        }
    }
}