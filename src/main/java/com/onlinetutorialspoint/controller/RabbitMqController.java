package com.onlinetutorialspoint.controller;

import com.onlinetutorialspoint.model.Item;
import com.onlinetutorialspoint.service.RabbitMqService;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {
    @Autowired
    RabbitMqService rabbitMqService;

    @PostMapping("/items")
    public ResponseEntity<String> postMessage(@RequestBody Item item){
        rabbitMqService.sendMessage(item);
        return new ResponseEntity<String>("Item pushed to RabbitMQ",HttpStatus.CREATED);
    }
    
   
    
        private AmqpTemplate amqpTemplate;    
        
        @GetMapping(value = "/producer")
        public String producer(@RequestParam("empName") String empName,
        		@RequestParam("empId") String empId,
        		@RequestParam("salary") String salary) {
    
            Item item=new Item();
    
            item.setItemName(empId);
    
            item.setCategory(empName);
    
            item.setDescription(salary);  
            rabbitMqService.sendMessage(item);
            
            return "Message sent to the RabbitMQ Successfully";
        }
}
