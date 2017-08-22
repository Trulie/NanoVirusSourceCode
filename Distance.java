package utility;

import model.CoOrdinate;

public class Distance {
    private int x1, x2;
    private int y1, y2;
    private int z1, z2;

    public int calculateDistance(CoOrdinate coOrdinate1, CoOrdinate coOrdinate2) {
        x1 = coOrdinate1.getX();
        y1 = coOrdinate1.getY();
        z1 = coOrdinate1.getZ();

        x2 = coOrdinate2.getX();
        y2 = coOrdinate2.getY();
        z2 = coOrdinate2.getZ();

        double x = Math.pow((double) (x1 - x2), (double) 2);
        double y = Math.pow((double) (y1 - y2), (double) 2);
        double z = Math.pow((double) (z1 - z2), (double) 2);

        double sumXYZ = x + y + z;

        int distance = (int) Math.sqrt(sumXYZ);

        return distance;
    }

}
