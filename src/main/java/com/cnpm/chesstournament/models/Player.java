package com.cnpm.chesstournament.models;

import java.util.Objects;

public class Player {
    
    private long id;
    private String name;
    private long birthYear;
    private String nationality;



    public Player() {
    }

    public Player(long id, String name, long birthYear, String nationality) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.nationality = nationality;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBirthYear() {
        return this.birthYear;
    }

    public void setBirthYear(long birthYear) {
        this.birthYear = birthYear;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Player id(long id) {
        this.id = id;
        return this;
    }

    public Player name(String name) {
        this.name = name;
        return this;
    }

    public Player birthYear(long birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public Player nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return id == player.id && Objects.equals(name, player.name) && birthYear == player.birthYear && Objects.equals(nationality, player.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthYear, nationality);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", birthYear='" + getBirthYear() + "'" +
            ", nationality='" + getNationality() + "'" +
            "}";
    }
    

}