package com.wileyedge.FlooringMastery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.wileyedge.FlooringMastery.controller.OrderController;

@SpringBootApplication
public class FlooringMasteryApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(FlooringMasteryApplication.class, args);
		
		OrderController controller = ctx.getBean("orderController", OrderController.class);
		controller.run();
	}

}
