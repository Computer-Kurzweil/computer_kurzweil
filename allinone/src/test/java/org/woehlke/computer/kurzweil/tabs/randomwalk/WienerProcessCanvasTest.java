package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

@Log
public class WienerProcessCanvasTest {

    @Test
    public void colorForAgeTest() throws Exception {
        log.info("colorForAgeTest");
        long age;
        int red = 0;
        int green = 0;
        int blue = 0;
        for(long n=0; n < Long.MAX_VALUE; n++){
            age = n % (256 * 256 * 256);
            log.info("age: "+age+" n: "+n);
            if(age == 0){
                red = 0;
                green = 0;
                blue = 0;
            } else {
                age = (256 * 256 * 256) - age;
                long blueL = (age / (256 * 256)) % (256 * 256 * 256);
                long greenL = (age / 256) % (256 * 256);
                long redL = (age % 256);
                log.info("redL: "+redL+" greenL: "+greenL+" blueL: "+blueL);
                red = (int)(redL);
                green = (int)(greenL);
                blue = (int)(blueL);
            }
            log.info("red: "+red+" green: "+green+" blue: "+blue);
            Color colorForAge = new Color(red, green, blue);
        }
        Assert.assertTrue(true);
        log.info("colorForAgeTest Done");
    }
}
