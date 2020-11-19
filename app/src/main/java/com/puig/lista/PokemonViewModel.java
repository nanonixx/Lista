package com.puig.lista;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PokemonViewModel extends AndroidViewModel {

    PokemonRep pokemonRep;

    MutableLiveData<List<Pokemon>> listPokemonMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Pokemon> pokemonSeleccionado = new MutableLiveData<>();

    void seleccionar (Pokemon pokemon){
        pokemonSeleccionado.setValue(pokemon);
    }

    MutableLiveData <Pokemon> seleccionado(){
        return pokemonSeleccionado;
    }

    MutableLiveData<List<Pokemon>> get() { return listPokemonMutableLiveData; }

    public PokemonViewModel(@NonNull Application application) {
        super(application);

        pokemonRep = new PokemonRep();

        listPokemonMutableLiveData.setValue(pokemonRep.getPokemonList());
    }

    MutableLiveData<List<Pokemon>> getListPokemonMutableLiveData() {
        return listPokemonMutableLiveData;
    }


}
