package org.example.junit5app.models;

import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    private User user;
    private List<Pokemon> pokemonList;
    private int count;

    public Pokedex(User user) {
        this.user = user;
        pokemonList = new ArrayList<>();
        count = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pokedex)) {
            return false;
        }
        Pokedex px = (Pokedex) obj;
        if (px.user == null) {
            return false;
        }

        return this.user.equals(px.getUser());
    }

    public User getUser() {
        return user;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public int getCount() {
        return count;
    }

    public void addPokemon(Pokemon pokemon) {
        pokemonList.add(pokemon);
        count++;
    }

    public void removePokemon(Pokemon pokemon) {
        pokemonList.remove(pokemon);
        count--;
    }
}
