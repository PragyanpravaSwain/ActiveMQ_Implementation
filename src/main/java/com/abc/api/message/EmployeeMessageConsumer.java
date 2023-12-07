package com.abc.api.message;


import com.abc.api.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMessageConsumer {

 @JmsListener(destination = "employee.queue.create",containerFactory = "defaultFactory")
 public void receiveMessage(Employee message){
  System.out.println("Employee: Id="+ message.getId() +" \n name=" + message.getName());
 }
}
