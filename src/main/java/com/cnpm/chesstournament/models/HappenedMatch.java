package com.cnpm.chesstournament.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * HappenedMatch
 */
public class HappenedMatch implements Serializable {

    private long id;
    private Player player;
    private Match match;
    private long result;


    public HappenedMatch() {
    }

    public HappenedMatch(long id, Player player, Match match, long result) {
        this.id = id;
        this.player = player;
        this.match = match;
        this.result = result;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return this.match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public long getResult() {
        return this.result;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public HappenedMatch id(long id) {
        this.id = id;
        return this;
    }

    public HappenedMatch player(Player player) {
        this.player = player;
        return this;
    }

    public HappenedMatch match(Match match) {
        this.match = match;
        return this;
    }

    public HappenedMatch result(long result) {
        this.result = result;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof HappenedMatch)) {
            return false;
        }
        HappenedMatch happenedMatch = (HappenedMatch) o;
        return id == happenedMatch.id && Objects.equals(player, happenedMatch.player) && Objects.equals(match, happenedMatch.match) && result == happenedMatch.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player, match, result);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", player='" + getPlayer() + "'" +
            ", match='" + getMatch() + "'" +
            ", result='" + getResult() + "'" +
            "}";
    }

}