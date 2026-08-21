package com.tinah.payment.model;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
public record Payment(UUID id,String idempotencyKey,String payerId,String merchantId,BigDecimal amount,String currency,PaymentStatus status,Instant createdAt) {}
