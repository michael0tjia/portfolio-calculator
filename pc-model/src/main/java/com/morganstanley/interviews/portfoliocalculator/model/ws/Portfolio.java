package com.morganstanley.interviews.portfoliocalculator.model.ws;

import java.util.List;
import java.util.Objects;

public class Portfolio {
    private List<Position> positions;
    private double nav;

    public Portfolio() {
    }

    public List<Position> getPositions() {
        return this.positions;
    }

    public void setPositions(final List<Position> positions) {
        this.positions = positions;
    }

    public double getNav() {
        return this.nav;
    }

    public void setNav(final double nav) {
        this.nav = nav;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Portfolio portfolio = (Portfolio) o;
        return Double.compare(portfolio.nav, this.nav) == 0 &&
                Objects.equals(this.positions, portfolio.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.positions, this.nav);
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "positions=" + this.positions +
                ", nav=" + this.nav +
                '}';
    }
}
