package org.example.junit5app.models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTest {
    Pokemon pokemon1;
    Pokemon pokemon2;

    @BeforeEach
    void initTest() {
        this.pokemon1 = new Pokemon("Pikachu", 20);
        this.pokemon2 = new Pokemon("Charmander", 30);
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
    @DisplayName("Pokemon must be the same")
    void testPokemonReference() {
        Pokemon pokemon = new Pokemon("Pikachu", 20);

        assertEquals(pokemon, pokemon1, () -> "Pokemons are not equal");
    }

    @Test
    @DisplayName("Pokemon must attack another Pokemon")
    void testAttackPokemon() {
        pokemon1.attack(pokemon2);

        int expected = 80;
        int actual = pokemon2.getHealth();

        assertEquals(expected, actual, () ->
                String.format("The Pokemon's (%s) health (%d) is not the expected (%d)", pokemon2.getName(), actual, expected));
    }

    @Test
    @DisplayName("Pokemon must attack another Pokemon until its die")
    void testAttackPokemonUntilItDie() {
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);

        assertEquals(false, pokemon2.isAlive());
        assertEquals(0, pokemon2.getHealth());
    }

    @Test
    @DisplayName("Pokemon must revive")
    void testRevivePokemon() {
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);
        pokemon1.attack(pokemon2);

        pokemon2.revive();

        assertEquals(true, pokemon2.isAlive());
        assertEquals(100, pokemon2.getHealth());
    }
}
