package com.cnpm.chesstournament.models;

import java.util.Objects;

public class EloStat extends Player {
    
    private long oldElo;
    private long newElo;


    public EloStat() {
    }

    public EloStat(long oldElo, long newElo) {
        this.oldElo = oldElo;
        this.newElo = newElo;
    }

    public EloStat(long id, String name, long oldElo, long newElo) {
        super(id, name);
        this.oldElo = oldElo;
        this.newElo = newElo;
    }

    public long getOldElo() {
        return this.oldElo;
    }

    public void setOldElo(long oldElo) {
        this.oldElo = oldElo;
    }

    public long getNewElo() {
        return this.newElo;
    }

    public void setNewElo(long newElo) {
        this.newElo = newElo;
    }

    public EloStat oldElo(long oldElo) {
        this.oldElo = oldElo;
        return this;
    }

    public EloStat newElo(long newElo) {
        this.newElo = newElo;
        return this;
    }

    public long getEloChange() {
        return newElo - oldElo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EloStat)) {
            return false;
        }
        EloStat eloStat = (EloStat) o;
        return oldElo == eloStat.oldElo && newElo == eloStat.newElo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldElo, newElo);
    }

    @Override
    public String toString() {
        return "{" +
            " oldElo='" + getOldElo() + "'" +
            ", newElo='" + getNewElo() + "'" +
            "}";
    }

}