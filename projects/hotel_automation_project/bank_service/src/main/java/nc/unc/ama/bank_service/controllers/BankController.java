package nc.unc.ama.bank_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/bank")
public class BankController {


    @RequestMapping("/transactionV")
    public Boolean getTrans() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
