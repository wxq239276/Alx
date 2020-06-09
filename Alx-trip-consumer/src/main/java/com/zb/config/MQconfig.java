package com.zb.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/5
 * @Version V1.0
 */
@Configuration
public class MQconfig {
    public static final String TRIPEXCHANGE="trip-exchange";
    public static final String TRIPQUEUE ="trip-queue";

    @Bean(TRIPEXCHANGE)
    public Exchange createExchange(){
        return ExchangeBuilder.topicExchange(TRIPEXCHANGE).durable(true).build();
    }

    @Bean(TRIPQUEUE)
    public Queue createQueue(){
        Queue queue =new Queue(TRIPQUEUE);
        return queue;
    }

    @Bean
    public Binding bindingOrder(@Qualifier(TRIPEXCHANGE) Exchange exchange , @Qualifier(TRIPQUEUE) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.trip.#").noargs();
    }


}
