package com.kornecki.controller;

import org.junit.Assert;
import org.junit.Test;

import javax.faces.model.SelectItem;
import java.util.List;

/**
 * @author karol.kornecki
 */
public class StaticDictionaryControllerTest {


    @Test
    public void testGetOrderTypeValues() {
        //given
        StaticDictionaryController controller = new StaticDictionaryController();
        //when
        List<SelectItem> list = controller.getOrderTypeValues();
        //then
        Assert.assertEquals(2, list.size());
    }


}
