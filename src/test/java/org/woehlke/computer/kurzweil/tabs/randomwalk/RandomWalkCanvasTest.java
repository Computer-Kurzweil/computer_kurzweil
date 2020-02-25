package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

@Log4j2
public class RandomWalkCanvasTest {

    @Test
    public void colorForAgeTest() {
        System.out.println("colorForAgeTest");
        String line = "-----------------------------------------------------------------------------------------";
        System.out.println(line);
        Random random = new Random();
        int values = 20000;
        int bound = 20000000;
        System.out.println("values: "+values);
        System.out.println("bound:  "+bound);
        System.out.println(line);
        long[] ageValues = new long[values];
        for(int i=0; i< values; i++){
            ageValues[i] = random.nextInt(bound);
        }
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
            String nLabel = " ".repeat((""+values).length()-(""+ n).length()) + n ;
            String ageLabel =  " ".repeat((""+bound).length()-(""+ age).length()) + age ;
            String redLabel =   ( red   >= 100 ) ? ""+red   : (( red   >= 10 ) ?" "+red   : "  "+red);
            String greenLabel = ( green >= 100 ) ? ""+green : (( green >= 10 ) ?" "+green : "  "+green);
            String blueLabel =  ( blue  >= 100 ) ? ""+blue  : (( blue  >= 10 ) ?" "+blue  : "  "+blue);
            System.out.println(" | n: "+nLabel+" |  age: "+ageLabel+" -> RGB( "+redLabel+", "+greenLabel+", "+blueLabel+" )");
            Assertions.assertTrue(red<256);
            Assertions.assertTrue(green<256);
            Assertions.assertTrue(blue<256);
            Color colorForAge = new Color(red, green, blue);
            Assertions.assertTrue(colorForAge.getRed()==red);
            Assertions.assertTrue(colorForAge.getGreen()==green);
            Assertions.assertTrue(colorForAge.getBlue()==blue);
        }
        Assertions.assertTrue(true);
        System.out.println(line);
        System.out.println("colorForAgeTest Done");
    }
}
