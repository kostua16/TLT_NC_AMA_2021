package com.example.banksystem.controller;


import com.example.banksystem.model.Client;
import com.example.banksystem.service.Bankomat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankomat")
public class BankomatController {

    @Autowired
    private Bankomat bankomat;

    //логин
    @PostMapping
    public ResponseEntity<Boolean> login(@RequestBody Client client)
    {
        return ResponseEntity.ok(bankomat.login(client));
    }

    //смотреть баланс
    @PostMapping("/check")
    public ResponseEntity<Integer> checkMoney(@RequestBody Client client)
    {

        return ResponseEntity.ok(bankomat.checkMoney(client));
    }


    //снять деньги с баланса
    @PostMapping("/withdraw/{money}")
    public ResponseEntity<Integer> withdrawMoney(@RequestBody Client client, @PathVariable("money") int money)
    {

        return ResponseEntity.ok(bankomat.withdrawMoney(client, money));
    }
}
