package com.star.stardemo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.stardemo.dataobject.StarUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CarControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void e() throws IOException {
//        new DataSource().getConnection().setAutoCommit(false);
        ObjectMapper objectMapper=new ObjectMapper();
        final StarUser starUser = new StarUser(1,"wangyu",26,"man");
        final String s = objectMapper.writeValueAsString(starUser);
        System.out.println("sssss"+s);

        String b="[{\"id\":1,\"userName\":\"wangyu\",\"userAge\":26,\"userSex\":\"man\",\"userSaex\":\"maaan\"},{\"id\":222,\"userName\":\"wangyu\",\"userAge\":26,\"userSex\":\"man\",\"userSaex\":\"maaan\"}]";
        final List<StarUser> starUser1 = objectMapper.readValue(b
                ,new TypeReference<List<StarUser>>(){});
       starUser1.stream().forEach(a-> System.out.println(a));


    }
}