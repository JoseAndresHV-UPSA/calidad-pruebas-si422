package org.example.junit5app.models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokedexTest {
    User user;
    Pokedex pokedex;

    @BeforeEach
    void initTest() {
        this.user = new User("Tito");
        pokedex = this.user.getPokedex();
        System.out.println("Test initialized");
    }

    @AfterEach
    void finalizeTest() {
        System.out.println("Test finished");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Initializing test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finishing test");
    }

    @Test
    @DisplayName("Pokedex must has a user")
    void testUser() {
        String expected = "Tito";
        String actual = pokedex.getUser().getName();

        assertEquals(expected, actual, () ->
                String.format("The user's name (%s) is not the expected (%s)", actual, expected));
    }

    @Test
    @DisplayName("Pokedex must be the same")
    void testPokedexReference() {
        User user2 = new User("Tito");
        Pokedex pokedex2 = user2.getPokedex();

        assertEquals(pokedex, pokedex2, () -> "Pokedexs are not equal");
    }

    @Test
    @DisplayName("Pokedex must add a Pokemon")
    void testAddPokemon() {
        String expected = "Pikachu";
        Pokemon pokemon = new Pokemon(expected, 20);
        pokedex.addPokemon(pokemon);

        assertTrue(user.getPokedex().getPokemonList()
                        .stream().anyMatch(
                                p -> p.getName().equals(expected)),
                String.format("The Pokedex has not added the Pokemon (%s)", expected));
    }

    @Test
    @DisplayName("Pokedex must remove a Pokemon")
    void testRemovePokemon() {
        String pokemonName = "Pikachu";
        Pokemon pokemon = new Pokemon(pokemonName, 20);
        pokedex.addPokemon(pokemon);
        pokedex.removePokemon(pokemon);

        assertTrue(!user.getPokedex().getPokemonList()
                        .stream().anyMatch(
                                p -> p.getName().equals(pokemonName)),
                String.format("The Pokedex has not removed the Pokemon (%s)", pokemonName));
    }

    @Test
    @DisplayName("Pokedex must count Pokemons")
    void testCountPokemons() {
        Pokemon pokemon1 = new Pokemon("Pikachu", 20);
        Pokemon pokemon2 = new Pokemon("Charmander", 20);
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);

        int expected = 2;
        int actual = pokedex.getCount();

        assertEquals(expected, actual, () -> String.format("The Pokedex's count (%d) is not the expected (%d)", actual, expected));
    }
}
