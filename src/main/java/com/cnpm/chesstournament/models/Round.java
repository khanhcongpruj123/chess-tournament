package com.cnpm.chesstournament.models;

import java.util.Objects;

public class Round {
    
    private long id;
    private long round;


    public Round() {
    }


    public Round(long id, long round) {
        this.id = id;
        this.round = round;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRound() {
        return this.round;
    }

    public void setRound(long round) {
        this.round = round;
    }

    public Round id(long id) {
        this.id = id;
        return this;
    }

    public Round round(long round) {
        this.round = round;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, round);
    }
    

    @Override
    public String toString() {
        return "Vòng " + round;
    }
    
}