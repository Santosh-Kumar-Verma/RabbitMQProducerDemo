package com.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.model.Employee;
import com.rabbitmq.service.RabbitMQSender;

@RestController
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer1")
	public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
	for (int i = 1;i<=1000;i++) {
		Employee emp=new Employee();
		emp.setEmpId(String.valueOf(i));
		emp.setEmpName("Name "+i);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rabbitMQSender.send(emp);
	}
	
	return "Message sent to the RabbitMQ JavaInUse Successfully";
	}
	@GetMapping(value = "/producer2")
	public String producer2(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
	for (int i = 1;i<=1000;i++) {
		Employee emp=new Employee();
		emp.setEmpId(String.valueOf(i+1));
		emp.setEmpName("Name "+i);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rabbitMQSender.send(emp);
	}
	
	return "Message sent to the RabbitMQ JavaInUse Successfully";
	}
}

