package com.cnpm.chesstournament.models;

import java.util.Objects;

public class Match {
    
    private long id;
    private Player player1;
    private Player player2;


    public Match() {
    }

    public Match(long id, Player player1, Player player2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Match id(long id) {
        this.id = id;
        return this;
    }

    public Match player1(Player player1) {
        this.player1 = player1;
        return this;
    }

    public Match player2(Player player2) {
        this.player2 = player2;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Match)) {
            return false;
        }
        Match match = (Match) o;
        return id == match.id && Objects.equals(player1, match.player1) && Objects.equals(player2, match.player2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player1, player2);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", player1='" + getPlayer1() + "'" +
            ", player2='" + getPlayer2() + "'" +
            "}";
    }

}