package baselhack.service;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/DB_Service")
public class StockService {

    @RequestMapping(value = "/stcck", method = RequestMethod.GET)
    public String getStock (){
        return "Hello World";
    }
}
