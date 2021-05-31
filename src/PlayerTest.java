import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void containsNull() {
        boolean result = Player.contains(null);
        Assert.assertFalse(result);
    }
    @Test
    public void containsEmptiness() {
        boolean result = Player.contains("");
        Assert.assertFalse(result);
    }
    @Test
    public void containsSmall() {
        boolean result = Player.contains("forward");
        Assert.assertFalse(result);
    }
    @Test
    public void containsUP() {
        boolean result = Player.contains("UP");
        Assert.assertTrue(result);
    }
    @Test
    public void containsDOWN() {
        boolean result = Player.contains("DOWN");
        Assert.assertTrue(result);
    }
    @Test
    public void containsFORWARD() {
        boolean result = Player.contains("FORWARD");
        Assert.assertTrue(result);
    }
    @Test
    public void containsBACK() {
        boolean result = Player.contains("BACK");
        Assert.assertTrue(result);
    }
    @Test
    public void containsLEFT() {
        boolean result = Player.contains("LEFT");
        Assert.assertTrue(result);
    }
    @Test
    public void containsRIGHT() {
        boolean result = Player.contains("RIGHT");
        Assert.assertTrue(result);
    }



}