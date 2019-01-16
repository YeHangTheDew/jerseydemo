package com.example.demo.rest;


import com.example.demo.bean.City;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/demo")
public class DemoRest {
    //path注解指定路径,get注解指定访问方式,produces注解指定了返回值类型，这里返回JSON
    @Path("/city")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public City get() {
        City city = new City();
        city.setId(1L);
        city.setCityName("beijing");
        city.setCityCode("001");
        System.out.println(city.toString());
        return city;
    }
}

