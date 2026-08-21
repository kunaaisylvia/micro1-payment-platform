package com.tinah.wallet.controller;

import com.tinah.wallet.model.Wallet;
import com.tinah.wallet.service.WalletService;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/wallets")
@Validated
public class WalletController {
    private final WalletService service;
    public WalletController(WalletService service){this.service=service;}
    @GetMapping("/{ownerId}") public Wallet get(@PathVariable String ownerId){return service.getOrCreate(ownerId);}
    @PostMapping("/{ownerId}/credit") public Wallet credit(@PathVariable String ownerId,@RequestParam @Positive BigDecimal amount){return service.credit(ownerId,amount);}
    @PostMapping("/{ownerId}/debit") public Wallet debit(@PathVariable String ownerId,@RequestParam @Positive BigDecimal amount){return service.debit(ownerId,amount);}
}
