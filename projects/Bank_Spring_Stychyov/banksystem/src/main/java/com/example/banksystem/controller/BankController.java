package com.example.banksystem.controller;

import com.example.banksystem.model.Client;
import com.example.banksystem.service.Bank;
import com.example.banksystem.service.Bankomat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController extends BankomatController {

    @Autowired
    private Bank bank;
    //пополнить
    @PostMapping("/add/{money}")
    public ResponseEntity<Integer> addMoney(@RequestBody Client client, @PathVariable("money") int money)
    {

        return ResponseEntity.ok(bank.addMoney(client, money));
    }

}
