package ch.hackathon.recipe.billscanning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BillScannerController {

    @RequestMapping(
            value = "/scanner",
            produces = "application/json"
    )
    public Map<String, Integer> billscanning() {
        return new Billscanning().getIngredients();
    }
}
