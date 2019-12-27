package ru.Robar3;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSql {
    SQlHomework sQlHomework;

    @BeforeTest
    public void initialize() {
        sQlHomework = new SQlHomework();
    }

    @Test
    public void testConnect() {
        Assert.assertTrue(sQlHomework.open());
    }
}
