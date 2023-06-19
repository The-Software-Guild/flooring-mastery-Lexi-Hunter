package com.wileyedge.FlooringMastery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.wileyedge.FlooringMastery.controller.OrderControllerImpl;

@SpringBootApplication
public class FlooringMasteryApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(FlooringMasteryApplication.class, args);
		
		OrderControllerImpl controller = ctx.getBean("orderControllerImpl", OrderControllerImpl.class);
		controller.run();
	}

}
