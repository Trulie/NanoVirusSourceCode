package model;

import utility.CreateMap;
import utility.GetRandomCell;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class NanoVirus {
    private CoOrdinate coOrdinate;

    public CoOrdinate getCoOrdinate() {
        return coOrdinate;
    }

    public void setCoOrdinate(CoOrdinate coOrdinate) {
        this.coOrdinate = coOrdinate;
    }

    public void move(Cell cell) {
        setCoOrdinate(cell.getCoOrdinate());
    }

    public String doNothing(Cell cell) {
        return "Nano Virus encountered the " + cell + ", No action taken.";
    }
}