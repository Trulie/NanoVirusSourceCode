package utility;

import model.Cell;
import model.Type;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreateMap {
   public Map<Type,List<Cell>> create100Cells(){
       Map<Type,List<Cell>> map = new LinkedHashMap();
       CellGenerator generator = new CellGenerator();

       List<Cell> redBloodCells = generator.createCells(70,Type.RED_BLOOD_CELL);
       List<Cell> whiteBloodCells = generator.createCells(25,Type.WHITE_BLOOD_CELL);
       List<Cell> tumorousCells =  generator.createCells(5,Type.TUMOROUS_CELL);

       map.put(Type.RED_BLOOD_CELL,redBloodCells);
       map.put(Type.WHITE_BLOOD_CELL,whiteBloodCells);
       map.put(Type.TUMOROUS_CELL,tumorousCells);

        return map;
    }
}
