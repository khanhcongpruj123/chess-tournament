package com.cnpm.chesstournament.models;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Ranking implements Serializable {
    
    private List<EloStat> eloStats;
    private Comparator eloComparator = new Comparator<EloStat>() {

        @Override
        public int compare(EloStat o1, EloStat o2) {
            if (o1.getNewElo() > o2.getNewElo()) return 1;
            if (o1.getNewElo() < o2.getNewElo()) return -1;
            return 0;
        }

    };


    public Ranking() {
    }

    public Ranking(final List<EloStat> eloStats) {
        this.eloStats = eloStats;
    }

    public List<EloStat> getEloStats() {
        return this.eloStats;
    }

    public void setEloStats(final List<EloStat> eloStats) {
        this.eloStats = eloStats;
        sortElo();
    }

    public Ranking eloStats(final List<EloStat> eloStats) {
        this.eloStats = eloStats;
        return this;
    }

    public void sortElo() {
        if (eloStats == null) return;
        eloStats.sort(eloComparator);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ranking)) {
            return false;
        }
        final Ranking ranking = (Ranking) o;
        return Objects.equals(eloStats, ranking.eloStats);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(eloStats);
    }

    @Override
    public String toString() {
        return "{" +
            " eloStats='" + getEloStats() + "'" +
            "}";
    }

}