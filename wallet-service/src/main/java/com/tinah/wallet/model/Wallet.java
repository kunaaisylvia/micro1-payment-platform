package com.tinah.wallet.model;
import java.math.BigDecimal; import java.util.UUID;
public record Wallet(UUID id,String ownerId,BigDecimal balance,String currency) {}
