package com.example.HelloWorld.controllers;

import com.example.HelloWorld.Message;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloWorldController {
    private final List<Message> messages = new ArrayList<>();

    @GetMapping("/api/hello/{name}")
    public String helloWorld(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping("/api/messages")
    public String addMessage(@RequestBody Message message) {
        messages.add(message);
        return message.toString();

    }

    @GetMapping("/api/messages")
    public List<Message> getMessages() {
        return messages;
    }

    @DeleteMapping("/api/messages/{id}")
    public String deleteMessage(@PathVariable String id) {
        Optional<Message> messageToDelete = messages.stream()
                .filter(message -> message.getId().equals(id))
                .findFirst();

        if (messageToDelete.isPresent()) {
            messages.remove(messageToDelete.get());
            return "Message deleted";
        } else {
            return "Message not found";
        }
    }

}
