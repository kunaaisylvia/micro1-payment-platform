package com.tinah.payment.controller;
import com.tinah.payment.dto.CreatePaymentRequest; import com.tinah.payment.model.Payment; import com.tinah.payment.service.PaymentService;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.UUID;
@RestController @RequestMapping("/api/v1/payments")
public class PaymentController { private final PaymentService service; public PaymentController(PaymentService service){this.service=service;}
 @PostMapping public ResponseEntity<Payment> create(@Valid @RequestBody CreatePaymentRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));}
 @GetMapping("/{id}") public Payment get(@PathVariable UUID id){return service.get(id);}
}
