package com.puig.lista;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puig.lista.databinding.FragmentRecyclerPokemonBinding;
import com.puig.lista.databinding.ViewholderPokemonBinding;

import java.util.List;

public class RecyclerPokemonFragment extends Fragment {


    private FragmentRecyclerPokemonBinding binding;
    private PokemonViewModel pokemonViewModel ;
    private NavController navController;

    class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

        List<Pokemon> pokemonList;


        public Pokemon getPokemon(int posicion){
            return pokemonList.get(posicion);
        }

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PokemonViewHolder(ViewholderPokemonBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

            Pokemon pokemon = pokemonList.get(position);

            holder.binding.nombre.setText(pokemon.nombre);
            holder.binding.tipos.setText(pokemon.tipos);
            holder.binding.numpok.setText(pokemon.numpokedex);

            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    pokemonViewModel.seleccionar(pokemon);
                    navController.navigate(R.id.action_recyclerPokemonFragment_to_mostrarPokemonFragment);
                }
            });

        }

        @Override
        public int getItemCount() {
            return pokemonList != null ? pokemonList.size() : 0;
        }

        public void setPokemonList(List<Pokemon> pokemonList){
            this.pokemonList = pokemonList;
            notifyDataSetChanged();
        }
    }


    class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPokemonBinding binding;

        public PokemonViewHolder(ViewholderPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (binding = FragmentRecyclerPokemonBinding.inflate(inflater, container, false)).getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        navController = Navigation.findNavController(view);

        PokemonAdapter pokemonAdapter = new PokemonAdapter();
        binding.recyclerView.setAdapter(pokemonAdapter);

        pokemonViewModel.getListPokemonMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemonList) {
                pokemonAdapter.setPokemonList(pokemonList);
            }
        });


    }
}