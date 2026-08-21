package com.tinah.payment.service;

import com.tinah.payment.dto.CreatePaymentRequest;
import com.tinah.payment.model.Payment;
import com.tinah.payment.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    @Test
    void createIsIdempotentForRepeatedKey() {
        PaymentRepository repository = mock(PaymentRepository.class);
        KafkaTemplate<String,String> kafka = mock(KafkaTemplate.class);
        CreatePaymentRequest request = new CreatePaymentRequest("key-1", "payer-1", "merchant-1", new BigDecimal("25.00"), "USD");
        Payment existing = new Payment(java.util.UUID.randomUUID(), "key-1", "payer-1", "merchant-1", new BigDecimal("25.00"), "USD", com.tinah.payment.model.PaymentStatus.COMPLETED, java.time.Instant.now());
        when(repository.findByIdempotencyKey("key-1")).thenReturn(Optional.of(existing));

        Payment result = new PaymentService(repository, kafka).create(request);

        assertEquals(existing.getId(), result.getId());
        verify(repository, never()).save(any());
        verifyNoInteractions(kafka);
    }
}
