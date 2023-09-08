package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/Timeout/{id}")
    @HystrixCommand
    /*@HystrixCommand(fallbackMethod = "paymentInfoTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})*/
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
//        int age=10/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }
    //兜底方法
    public String paymentInfoTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错检查自己 o(╥﹏╥)o~";
    }
    //global fallback
    //下面是全局fallback方法
    public  String payment_Global_FallbackMethod(){
        return  "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }
    //解决代码耦合度高问题
}
