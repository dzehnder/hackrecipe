package com.hackrecipe.billscanning.resource;


import com.hackrecipe.billscanning.Service.DB_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/DB_Service")
public class StockResource {

    @Autowired
    DB_Service db_service;

    @RequestMapping(value = "/stock", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Integer>> updateStock (@RequestBody Map<String, Integer> stockMap){
        db_service.addIngredients(stockMap.keySet());
        db_service.addStocks(stockMap);
        return new ResponseEntity<>(stockMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getStock (){
        List<String> stock = db_service.getStock();
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}

