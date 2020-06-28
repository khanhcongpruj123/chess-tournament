package com.cnpm.chesstournament.models;

import java.util.Objects;

public class Ranking extends Player{
    
   private long point;
   private long totalPointOfCompetitor;
   private long elo;


    public Ranking() {
    }

    public Ranking(long id, String name, long birthYear, String nationality, long point, long totalPointOfCompetitor, long elo) {
        super(id, name, birthYear, nationality);
        this.point = point;
        this.totalPointOfCompetitor = totalPointOfCompetitor;
        this.elo = elo;
    }

    public Ranking(long point, long totalPointOfCompetitor, long elo) {
        this.point = point;
        this.totalPointOfCompetitor = totalPointOfCompetitor;
        this.elo = elo;
    }

    public long getPoint() {
        return this.point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public long getTotalPointOfCompetitor() {
        return this.totalPointOfCompetitor;
    }

    public void setTotalPointOfCompetitor(long totalPointOfCompetitor) {
        this.totalPointOfCompetitor = totalPointOfCompetitor;
    }

    public long getElo() {
        return this.elo;
    }

    public void setElo(long elo) {
        this.elo = elo;
    }

    public Ranking point(long point) {
        this.point = point;
        return this;
    }

    public Ranking totalPointOfCompetitor(long totalPointOfCompetitor) {
        this.totalPointOfCompetitor = totalPointOfCompetitor;
        return this;
    }

    public Ranking elo(long elo) {
        this.elo = elo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ranking)) {
            return false;
        }
        Ranking ranking = (Ranking) o;
        return point == ranking.point && totalPointOfCompetitor == ranking.totalPointOfCompetitor && elo == ranking.elo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, totalPointOfCompetitor, elo);
    }

    @Override
    public String toString() {
        return "{" +
            " point='" + getPoint() + "'" +
            ", totalPointOfCompetitor='" + getTotalPointOfCompetitor() + "'" +
            ", elo='" + getElo() + "'" +
            "}";
    }

}