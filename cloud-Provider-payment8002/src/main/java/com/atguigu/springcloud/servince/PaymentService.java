package com.atguigu.springcloud.servince;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
