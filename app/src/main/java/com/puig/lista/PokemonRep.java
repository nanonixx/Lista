package com.puig.lista;

import java.util.ArrayList;
import java.util.List;

public class PokemonRep {

    List<Pokemon> pokemonList = new ArrayList<>();

    interface Callback {
        void finish(List<Pokemon> pokemonList);
    }

    PokemonRep(){
        pokemonList.add(new Pokemon("Bulbasaur", "Planta - Veneno", 1));
        pokemonList.add(new Pokemon("Ivysaur", "Planta - Veneno", 2));
        pokemonList.add(new Pokemon("Venusaur", "Planta - Veneno", 3));
        pokemonList.add(new Pokemon("Charmander", "Fuego", 4));
        pokemonList.add(new Pokemon("Charmeleon", "Fuego", 5));
    }

    List<Pokemon> getPokemonList(){
        return pokemonList;
    }

}
