package com.tinah.wallet.service;

import com.tinah.wallet.model.Wallet;
import com.tinah.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository repository;
    public WalletService(WalletRepository repository) { this.repository = repository; }

    @Transactional
    public Wallet getOrCreate(String ownerId) {
        return repository.findByOwnerIdAndCurrency(ownerId, "USD")
                .orElseGet(() -> repository.save(new Wallet(UUID.randomUUID(), ownerId, BigDecimal.ZERO, "USD")));
    }

    @Transactional
    public Wallet credit(String ownerId, BigDecimal amount) {
        Wallet wallet = getOrCreate(ownerId);
        wallet.credit(amount);
        return repository.save(wallet);
    }

    @Transactional
    public Wallet debit(String ownerId, BigDecimal amount) {
        Wallet wallet = getOrCreate(ownerId);
        wallet.debit(amount);
        return repository.save(wallet);
    }
}
