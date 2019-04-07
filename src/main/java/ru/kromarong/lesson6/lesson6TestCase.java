package ru.kromarong.lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class lesson6TestCase {

    private lesson6 hw;

    @Before
    public void startTest(){
        hw = new lesson6();
    }

    @Test (expected = RuntimeException.class)
    public void testTask1_without4(){
        Assert.assertArrayEquals(new int[0],hw.find4(new int[]{1,2,5,2,8,1}));
    }

    @Test (expected = RuntimeException.class)
    public void testTask1_epmty(){
        Assert.assertArrayEquals(new int[0],hw.find4(new int[0]));
    }

    @Test
    public void testTask1_normal(){
        Assert.assertArrayEquals(new int[]{2,8,1},hw.find4(new int[]{1,2,5,4,2,8,1}));
    }

    @Test
    public void testTask1_last_4(){
        Assert.assertArrayEquals(new int[0],hw.find4(new int[]{1,2,5,4,2,8,1,4}));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void testTask2_empty(){
        Assert.assertEquals(false,hw.check(new int[0]));
    }

    @Test
    public void testTask2_without_1(){
        Assert.assertEquals(false,hw.check(new int[]{4,4,4}));
    }

    @Test
    public void testTask2_without_4(){
        Assert.assertEquals(false,hw.check(new int[]{1,1,1}));
    }

    @Test
    public void testTask2_with_other_number(){
        Assert.assertEquals(false,hw.check(new int[]{4,1,2}));
    }

    @Test
    public void testTask2_normal(){
        Assert.assertEquals(true,hw.check(new int[]{4,1,4}));
    }
}
