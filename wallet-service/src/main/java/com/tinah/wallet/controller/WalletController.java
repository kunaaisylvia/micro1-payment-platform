package com.tinah.wallet.controller;
import com.tinah.wallet.model.Wallet; import com.tinah.wallet.service.WalletService; import org.springframework.web.bind.annotation.*; import java.math.BigDecimal;
@RestController @RequestMapping("/api/v1/wallets") public class WalletController {private final WalletService service; public WalletController(WalletService service){this.service=service;}
 @GetMapping("/{ownerId}") public Wallet get(@PathVariable String ownerId){return service.getOrCreate(ownerId);}
 @PostMapping("/{ownerId}/credit") public Wallet credit(@PathVariable String ownerId,@RequestParam BigDecimal amount){return service.credit(ownerId,amount);}}
