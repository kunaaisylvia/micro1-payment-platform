package com.tinah.wallet.repository;

import com.tinah.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByOwnerIdAndCurrency(String ownerId, String currency);
}
