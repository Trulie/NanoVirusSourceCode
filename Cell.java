package model;

public class Cell {
    private CoOrdinate coOrdinate;
    private Type type;

    public CoOrdinate getCoOrdinate() {
        return coOrdinate;
    }

    public void setCoOrdinate(CoOrdinate coOrdinate) {
        this.coOrdinate = coOrdinate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " at " + coOrdinate;
    }
}
