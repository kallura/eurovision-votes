package org.eurovision.model;

import java.util.Objects;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public class Vote {

    private String country;
    private String votedFor;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(String votedFor) {
        this.votedFor = votedFor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vote vote = (Vote) o;
        return Objects.equals(country, vote.country)
                && Objects.equals(votedFor, vote.votedFor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, votedFor);
    }
}
