package com.tinah.payment.service;
import com.tinah.payment.dto.CreatePaymentRequest;
import com.tinah.payment.model.*;
import org.springframework.stereotype.Service;
import java.time.Instant; import java.util.*; import java.util.concurrent.ConcurrentHashMap;
@Service
public class PaymentService {
 private final Map<UUID,Payment> payments=new ConcurrentHashMap<>(); private final Map<String,UUID> keys=new ConcurrentHashMap<>();
 public Payment create(CreatePaymentRequest r){
   UUID existing=keys.get(r.idempotencyKey()); if(existing!=null)return payments.get(existing);
   Payment p=new Payment(UUID.randomUUID(),r.idempotencyKey(),r.payerId(),r.merchantId(),r.amount(),r.currency(),PaymentStatus.COMPLETED,Instant.now());
   payments.put(p.id(),p); keys.put(r.idempotencyKey(),p.id()); return p;
 }
 public Payment get(UUID id){return Optional.ofNullable(payments.get(id)).orElseThrow(()->new NoSuchElementException("Payment not found: "+id));}
}
