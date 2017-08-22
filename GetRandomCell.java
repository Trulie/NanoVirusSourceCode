package utility;

import model.Cell;
import model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GetRandomCell {

    public Cell getRandomCell(Map map,Type type){
        Map<Type, List<Cell>> mapList = map;
        List<Cell> cellList = mapList.get(type);

        Cell cell = new Cell();
        Random random = new Random();

        int randomIndex = random.nextInt(cellList.size());

        cell = cellList.get(randomIndex);

        return cell;
    }

}
