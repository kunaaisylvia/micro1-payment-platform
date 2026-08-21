package com.tinah.payment.service;

import com.tinah.payment.dto.CreatePaymentRequest;
import com.tinah.payment.model.Payment;
import com.tinah.payment.model.PaymentStatus;
import com.tinah.payment.repository.PaymentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentService(PaymentRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @CircuitBreaker(name = "paymentProcessing", fallbackMethod = "paymentFallback")
    public Payment create(CreatePaymentRequest request) {
        return repository.findByIdempotencyKey(request.idempotencyKey()).orElseGet(() -> {
            Payment payment = new Payment(UUID.randomUUID(), request.idempotencyKey(), request.payerId(), request.merchantId(),
                    request.amount(), request.currency(), PaymentStatus.COMPLETED, Instant.now());
            Payment saved = repository.save(payment);
            kafkaTemplate.send("payment-events", saved.getId().toString(),
                    "PAYMENT_COMPLETED|" + saved.getId() + "|" + saved.getMerchantId() + "|" + saved.getAmount());
            return saved;
        });
    }

    public Payment paymentFallback(CreatePaymentRequest request, Throwable throwable) {
        throw new IllegalStateException("Payment service temporarily unavailable", throwable);
    }

    public Payment get(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Payment not found: " + id));
    }
}
