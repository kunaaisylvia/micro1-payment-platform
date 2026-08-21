package com.tinah.wallet.service;
import com.tinah.wallet.model.Wallet; import org.springframework.stereotype.Service; import java.math.BigDecimal; import java.util.*; import java.util.concurrent.ConcurrentHashMap;
@Service public class WalletService {private final Map<String,Wallet>wallets=new ConcurrentHashMap<>();
 public Wallet getOrCreate(String ownerId){return wallets.computeIfAbsent(ownerId,k->new Wallet(UUID.randomUUID(),k,BigDecimal.ZERO,"USD"));}
 public Wallet credit(String ownerId,BigDecimal amount){Wallet w=getOrCreate(ownerId); Wallet updated=new Wallet(w.id(),w.ownerId(),w.balance().add(amount),w.currency()); wallets.put(ownerId,updated); return updated;}}
