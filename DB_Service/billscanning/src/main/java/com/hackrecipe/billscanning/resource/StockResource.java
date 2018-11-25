package com.hackrecipe.billscanning.resource;


import com.hackrecipe.billscanning.Service.DB_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/DB_Service")
public class StockResource {

    @Autowired
    DB_Service db_service;

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    @ResponseBody
    public String getStock (){
        db_service.updateStock();
        return db_service.getStock();
    }
}

