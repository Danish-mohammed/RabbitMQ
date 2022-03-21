//package com.bridgelabz.userregistration.jwtoperations;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.bridgelabz.userregistration.model.UserEntity;
//
//@Service
//public class RabbitMqSender {
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//    @Value("${spring.rabbitmq.exchange}")
//    private String exchange;
//    @Value("${spring.rabbitmq.routingkey}")
//    private String routingkey;
//    public void send(UserEntity user){
//        rabbitTemplate.convertAndSend(exchange,routingkey, user);
//    }
//
//
//}
