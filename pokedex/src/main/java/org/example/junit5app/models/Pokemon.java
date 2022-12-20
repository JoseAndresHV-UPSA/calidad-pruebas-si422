package org.example.junit5app.models;

public class Pokemon {
    private String name;
    private int attackPoints;
    private int health;
    private boolean isAlive;

    public Pokemon(String name, int attackPoints) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.health = 100;
        this.isAlive = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pokemon)) {
            return false;
        }
        Pokemon p = (Pokemon) obj;
        if (p.name == null) {
            return false;
        }

        return this.name.equals(p.getName())
                && this.attackPoints == p.getAttackPoints()
                && this.health == p.getHealth()
                && this.isAlive == p.isAlive();
    }

    public String getName() {
        return name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void attack(Pokemon pokemon) {
        pokemon.receiveDamage(this);
    }

    public void die() {
        isAlive = false;
        health = 0;
    }

    public void revive() {
        isAlive = true;
        health = 100;
    }

    public void receiveDamage(Pokemon pokemon) {
        int newHealth = this.health - pokemon.getAttackPoints();
        if (newHealth <= 0) {
            die();
        } else {
            this.health = newHealth;
        }
    }
}
