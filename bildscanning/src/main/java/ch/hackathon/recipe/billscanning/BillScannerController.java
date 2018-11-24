package ch.hackathon.recipe.billscanning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillScannerController {

    @RequestMapping(
            value = "/scanner",
            produces = "application/json"
    )
    public Billscanning billscanning() {
        return new Billscanning();
    }
}
