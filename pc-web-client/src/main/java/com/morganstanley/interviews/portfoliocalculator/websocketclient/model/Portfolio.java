package com.morganstanley.interviews.portfoliocalculator.websocketclient.model;

import java.util.List;

public class Portfolio {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
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
    public String toString() {
        return "Portfolio{" + LINE_SEPARATOR +
                "positions=" + this.positions +
                ", nav=" + this.nav +
                '}';
    }
}
