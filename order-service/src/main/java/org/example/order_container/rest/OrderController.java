package org.example.order_container.rest;

import jakarta.validation.Valid;
import org.example.order_domain.order_application_service.dto.OrderCreatedDto;
import org.example.order_domain.order_application_service.dto.OrderResponseDto;
import org.example.order_domain.order_application_service.handler.CreatedOrderUseCase;
import org.example.order_domain.order_application_service.handler.GetOrderDetailUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private CreatedOrderUseCase createdOrderUseCase;
    private GetOrderDetailUseCase getOrderDetailUseCase;
    public OrderController(CreatedOrderUseCase createdOrderUseCase,GetOrderDetailUseCase getOrderDetailUseCase) {
        this.createdOrderUseCase=createdOrderUseCase;
        this.getOrderDetailUseCase=getOrderDetailUseCase;
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrderById(@PathVariable java.util.UUID id) {
        return getOrderDetailUseCase.execute(id);
    }

    @PostMapping
    public OrderResponseDto createOrder(@Valid  @RequestBody OrderCreatedDto dto) {
        return createdOrderUseCase.execute(dto);
    }
}
