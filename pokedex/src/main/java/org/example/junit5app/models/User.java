package org.example.junit5app.models;

public class User {
    private String name;
    private Pokedex pokedex;

    public User(String name) {
        this.name = name;
        pokedex = new Pokedex(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User u = (User) obj;
        if (u.name == null || u.pokedex == null) {
            return false;
        }

        return this.name.equals(u.getName());
    }

    public String getName() {
        return name;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void catchPokemon(Pokemon pokemon) {
        pokedex.addPokemon(pokemon);
    }
}
