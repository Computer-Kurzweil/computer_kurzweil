package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

@Log4j2
public class RandomWalkCanvasTest {

    private Random random = new Random();

    private void colorForAge(long[] ageValues){
        long age;
        int red = 0;
        int green = 0;
        int blue = 0;
        long myage;
        long mybyte;
        for(int n=0; n < ageValues.length; n++){
            age = ageValues[n] % (256 * 256 * 256);
            if(age == 0){
                red = 0;
                green = 0;
                blue = 0;
            } else {
                myage = (256 * 256 * 256) - age;
                mybyte = myage % 256;
                red = (int)(mybyte);
                myage /= 256;
                mybyte = myage % 256;
                green = (int)(mybyte);
                myage /= 256;
                mybyte = myage % 256;
                blue = (int)(mybyte);
            }
            log.info(" | n: "+n+" |  age: "+age+" -> RGB( "+red+", "+green+", "+blue+" ) | ");
            Assertions.assertTrue(red<256);
            Assertions.assertTrue(green<256);
            Assertions.assertTrue(blue<256);
            Color colorForAge = new Color(red, green, blue);
            Assertions.assertTrue(colorForAge.getRed()==red);
            Assertions.assertTrue(colorForAge.getGreen()==green);
            Assertions.assertTrue(colorForAge.getBlue()==blue);
        }
    }

    @Test
    public void colorForAgeTest() {
        log.info("colorForAgeTest");
        int values = 1000;
        int bound = 20000000;
        long[] age = new long[values];
        for(int i=0; i< values; i++){
            age[i] = random.nextInt(bound);
        }
        this.colorForAge(age);
        Assertions.assertTrue(true);
        log.info("colorForAgeTest Done");
    }
}
