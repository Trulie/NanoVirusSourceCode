package main;

import model.Cell;
import model.NanoVirus;
import model.Type;
import utility.CreateMap;
import utility.Distance;
import utility.GetRandomCell;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class App {
    private static int cycle;
    private static int getDistance;
    private static ArrayList<String> log = new ArrayList<>();

    NanoVirus nanoVirus = new NanoVirus();

    Map<Type, List<Cell>> map = new LinkedHashMap();
    List<Cell> redBloodCells = new ArrayList();
    List<Cell> whiteBloodCells = new ArrayList();
    List<Cell> tumorousCells = new ArrayList();

    public static void main(String[] args) throws IOException {

        App run = new App();
        log.add("*****************************************************************************");
        log.add("Nano virus Application started");
        log.add("*******************************************************************************");

        run.generateCells();
        log.add("100 cells generated, 70% Red Blood cells, 25% white blood cells and 5% Tumorous");
        log.add("_______________________________________________________________________________");
        run.introduceNanoVirus();
        log.add("Nano virus has been introduced into the system." +
                "Nano virus starting location is "+run.nanoVirus.getCoOrdinate()+" in a red blood cell.");
        while (run.cleanCellsPresent()) {
            Cell cell = run.nanoVirusGoToNextCell();
            log.add("Nano virus moved to next random cell within 5000 units distance." +
                    "Calculated distance is " +getDistance+" units.");

            if (cycle > 4) {
                run.tumorousCellInfection();
                cycle = 0;
            }
            run.nanoVirusActionOnCell(cell);
          }
    }

    public void generateCells() {
        CreateMap generateHundredCells = new CreateMap();
        map = generateHundredCells.create100Cells();
        redBloodCells = map.get(Type.RED_BLOOD_CELL);
        whiteBloodCells = map.get(Type.WHITE_BLOOD_CELL);
        tumorousCells = map.get(Type.TUMOROUS_CELL);
    }

    public void introduceNanoVirus() {
        Cell cell = new Cell();
        GetRandomCell randomRedCell = new GetRandomCell();

        cell = randomRedCell.getRandomCell(map, Type.RED_BLOOD_CELL);
        nanoVirus.setCoOrdinate(cell.getCoOrdinate());
    }

    public boolean isListEmpty(Type type) {
        List<Cell> cellList = map.get(type);
        return cellList.isEmpty();
    }


    public boolean cleanCellsPresent() {
        return !redBloodCells.isEmpty() || !whiteBloodCells.isEmpty();
    }

    public void nanoVirusActionOnCell(Cell cell) throws IOException {
        if (cell.getType() == Type.TUMOROUS_CELL) {
            tumorousCells.remove(cell);

            if (tumorousCells.size() == 0) {
                log.add("_______________________________________________________________________________");
                log.add("There are no more tumorous cells in the system, Nano virus was successful.");
                System.out.println("Execution Complete.");
                log.add("_______________________________________________________________________________");
                File file = new File("Nano Virus Application Log.txt");
                file.createNewFile();

                FileWriter writer = new FileWriter(file);
                for (String log : log) {
                    writer.write(log );
                }
                System.exit(0);
            }
            log.add("Nano virus killed a Tumorous cell located at" +nanoVirus.getCoOrdinate());

        } else {
           log.add(nanoVirus.doNothing(cell));
        }
        cycle++;
    }

    public Cell nanoVirusGoToNextCell() {
        Cell cell = new Cell();
        GetRandomCell randomCell = new GetRandomCell();
        Distance distance = new Distance();

        getDistance = Integer.MAX_VALUE;

        while (getDistance > 5000) {
            Type type = Type.generateRandomCellType();
                List<Cell> cellList = cellListType(type);

            for (Cell c : cellList) {
                getDistance = distance.calculateDistance(nanoVirus.getCoOrdinate(), c.getCoOrdinate());
                cell = c;
                if (getDistance <= 5000 && getDistance != 0) {
                    break;
                }
            }
        }
        nanoVirus.move(cell);
        cycle++;
        return cell;
    }

    public void tumorousCellInfection() throws IOException {
        List<Cell> cellsAffected = new ArrayList<Cell>();
        Distance distance = new Distance();

        for (Cell tCell : tumorousCells) {
             Cell nearestCell = null;
            int nearestCellDistance = Integer.MAX_VALUE;

            if (redBloodCells.size() != 0) {
                for (Cell rCell : redBloodCells) {
                    int distanceBetweenRedCellAndTumorous =
                            distance.calculateDistance(tCell.getCoOrdinate(), rCell.getCoOrdinate());
                    if (nearestCellDistance > distanceBetweenRedCellAndTumorous) {
                        nearestCellDistance = distanceBetweenRedCellAndTumorous;
                        nearestCell = rCell;
                    }
                }
                cellsAffected.add(nearestCell);
                redBloodCells.remove(nearestCell);
                log.add("The tumorous cells have infected "+ cellsAffected.size()+ "\tcells.");

            } else if (whiteBloodCells.size() != 0) {
                for (Cell wCell : whiteBloodCells) {
                    int distanceBetweenRedCellAndTumorous =
                            distance.calculateDistance(wCell.getCoOrdinate(), wCell.getCoOrdinate());
                    if (nearestCellDistance > distanceBetweenRedCellAndTumorous) {
                        nearestCellDistance = distanceBetweenRedCellAndTumorous;
                        nearestCell = wCell;
                    }
                }
                cellsAffected.add(nearestCell);
                whiteBloodCells.remove(nearestCell);
                log.add("The tumorous cells have infected "+ cellsAffected.size()+ "\tcells.");

            } else if (whiteBloodCells.size() == 0 && redBloodCells.size() == 0) {
                if (cellsAffected.size() != 0) {
                    for (Cell cell : cellsAffected) {
                        cell.setType(Type.TUMOROUS_CELL);
                        tumorousCells.add(cell);
                    }
                }
                log.add("-----------------------------------------------------------------------------------");
                log.add("Status: Tumorous: " + tumorousCells.size() + " | " +
                        "Red: " + redBloodCells.size() + " | " +
                        "White: " + whiteBloodCells.size());
                log.add("All cells have been infected, Nano virus unsuccessful");
                log.add("-----------------------------------------------------------------------------------");

                File file = new File("Nano Virus Application Log.txt");
                file.createNewFile();

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (String log : log) {
                    writer.write(log );
                    writer.newLine();
                    writer.newLine();
                }
                writer.flush();
                writer.close();
                System.exit(0);
            }

        }

        for (Cell cell : cellsAffected) {
            cell.setType(Type.TUMOROUS_CELL);
            tumorousCells.add(cell);
        }
  }

    public List<Cell> cellListType(Type type) {

        if (type == Type.RED_BLOOD_CELL) {
            return redBloodCells;
        }
        else if (type == Type.WHITE_BLOOD_CELL) {
          return whiteBloodCells;
        }
       else  {
           return tumorousCells;
        }
    }

}