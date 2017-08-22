package model;

public enum Type {
    RED_BLOOD_CELL,
    WHITE_BLOOD_CELL,
    TUMOROUS_CELL;

    /*source: stack Overflow*/
    public  static Type generateRandomCellType(){
        return values()[(int) (Math.random() * values().length)];
    }
}
