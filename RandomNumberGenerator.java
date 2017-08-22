package utility;

import java.util.Random;

public class RandomNumberGenerator {
    private int number;

    public int generateNumberBetween1And5000(){
        Random random = new Random();
        number = 1 + random.nextInt(5000);

        return number;
    }
}
