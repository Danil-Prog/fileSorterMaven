package com.testCFT.app;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class OperationServiceTest extends TestCase {

    private final OperationService operationService = new OperationService();

    @Test
    public void testCheckListType() {
        String[] o = new String[] {"1", "2", "3", "10", "str"};
        String actual = operationService.checkListType(o, "-i");
        String expected = "-s";
        Assert.assertEquals(expected, actual);

        o = new String[] {"1", "2", "3", "10", "23"};
        actual = operationService.checkListType(o, "-i");
        expected = "-i";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCheckListMode() {
        boolean actual = operationService.checkListMode("-a");
        Assert.assertEquals(true, actual);

        actual = operationService.checkListMode("-d");
        Assert.assertEquals(false, actual);
    }
}
