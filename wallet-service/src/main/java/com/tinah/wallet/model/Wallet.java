package com.tinah.wallet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallets", indexes = @Index(name = "idx_wallet_owner_currency", columnList = "ownerId,currency", unique = true))
public class Wallet {
    @Id private UUID id;
    @Column(nullable = false) private String ownerId;
    @Column(nullable = false, precision = 19, scale = 4) private BigDecimal balance;
    @Column(nullable = false, length = 3) private String currency;
    protected Wallet() {}
    public Wallet(UUID id, String ownerId, BigDecimal balance, String currency) { this.id=id; this.ownerId=ownerId; this.balance=balance; this.currency=currency; }
    public UUID getId(){return id;} public String getOwnerId(){return ownerId;} public BigDecimal getBalance(){return balance;} public String getCurrency(){return currency;}
    public void debit(BigDecimal amount){ if(balance.compareTo(amount)<0) throw new IllegalStateException("Insufficient wallet balance"); balance=balance.subtract(amount); }
    public void credit(BigDecimal amount){ balance=balance.add(amount); }
}
