package com.atguigu.springcloud.servince.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.servince.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private   PaymentDao paymentDao;



    public int create(Payment payment) {
        return paymentDao.create(payment);
    }


    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
