package com.tinah.payment.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payments", indexes = @Index(name = "idx_payment_idempotency", columnList = "idempotencyKey", unique = true))
public class Payment {
    @Id
    private UUID id;
    @Column(nullable = false, unique = true)
    private String idempotencyKey;
    @Column(nullable = false)
    private String payerId;
    @Column(nullable = false)
    private String merchantId;
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;
    @Column(nullable = false, length = 3)
    private String currency;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;
    @Column(nullable = false)
    private Instant createdAt;

    protected Payment() {}
    public Payment(UUID id, String idempotencyKey, String payerId, String merchantId, BigDecimal amount, String currency, PaymentStatus status, Instant createdAt) {
        this.id = id; this.idempotencyKey = idempotencyKey; this.payerId = payerId; this.merchantId = merchantId;
        this.amount = amount; this.currency = currency; this.status = status; this.createdAt = createdAt;
    }
    public UUID getId(){ return id; }
    public String getIdempotencyKey(){ return idempotencyKey; }
    public String getPayerId(){ return payerId; }
    public String getMerchantId(){ return merchantId; }
    public BigDecimal getAmount(){ return amount; }
    public String getCurrency(){ return currency; }
    public PaymentStatus getStatus(){ return status; }
    public Instant getCreatedAt(){ return createdAt; }
    public void setStatus(PaymentStatus status){ this.status = status; }
}
