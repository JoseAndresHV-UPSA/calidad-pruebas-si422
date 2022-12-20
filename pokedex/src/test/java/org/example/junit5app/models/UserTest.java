package org.example.junit5app.models;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user;

    @BeforeEach
    void initTest() {
        this.user = new User("Tito");
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
    @DisplayName("User must has a name")
    void testUserName() {
        String expected = "Tito";
        String actual = user.getName();

        assertEquals(expected, actual, () ->
                String.format("The name (%s) is not the expected (%s)", actual, expected));
    }

    @Test
    @DisplayName("Pokedex must not be null")
    void testPokedex() {
        Pokedex expected = new Pokedex(user);
        Pokedex actual = user.getPokedex();

        assertAll(
                () ->  assertNotNull(actual, () ->
                        String.format("The user (%s) has not a Pokedex", user.getName())),
                () -> assertEquals(expected, actual, () -> "The Pokedex are not the same")
        );
    }

    @Test
    @DisplayName("Users must be the same")
    void testUserReference() {
        User user2 = new User("Tito");

        assertEquals(user, user2, () -> "Users are not equal");
    }

    @Test
    @DisplayName("User must catch a Pokemon")
    void testCatchPokemon() {
        String expected = "Pikachu";
        Pokemon pokemon = new Pokemon(expected, 20);
        user.catchPokemon(pokemon);

        assertTrue(user.getPokedex().getPokemonList()
                .stream().anyMatch(
                        p -> p.getName().equals(expected)),
                String.format("The user has not caught the Pokemon (%s)", expected));
    }
}
