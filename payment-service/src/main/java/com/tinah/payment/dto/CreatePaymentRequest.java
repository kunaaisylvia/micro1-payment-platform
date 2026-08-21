package com.tinah.payment.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CreatePaymentRequest(@NotBlank String idempotencyKey,@NotBlank String payerId,@NotBlank String merchantId,@NotNull @DecimalMin("0.01") BigDecimal amount,@NotBlank @Pattern(regexp="[A-Z]{3}") String currency) {}
