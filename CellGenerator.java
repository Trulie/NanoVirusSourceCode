package utility;

import model.Cell;
import model.CoOrdinate;
import model.Type;

import java.util.ArrayList;
import java.util.List;

public class CellGenerator {

    public List<Cell> createCells(int number, Type type) {
        List<Cell> cellList = new ArrayList();

        for (int i = 0; i < number; i++) {
            CoOrdinate coOrdinate = new CoOrdinate();
            Cell cell = new Cell();
            RandomNumberGenerator generator = new RandomNumberGenerator();

            coOrdinate.setX(generator.generateNumberBetween1And5000());
            coOrdinate.setY(generator.generateNumberBetween1And5000());
            coOrdinate.setZ(generator.generateNumberBetween1And5000());

            cell.setType(type);
            cell.setCoOrdinate(coOrdinate);
            cellList.add(cell);
        }

        return cellList;
    }

}
