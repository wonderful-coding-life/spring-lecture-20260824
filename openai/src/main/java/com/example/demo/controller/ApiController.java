package com.example.demo.controller;

import com.example.demo.tool.ProductOrderTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@RestController
public class ApiController {
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private ProductOrderTool productOrderTool;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value="/chats", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> postChats(@RequestBody String message) {
        String user ="seojun";
        return chatClient.prompt()
                .system("마크다운 말고 순수한 텍스트만 사용해. 너는 캠퍼스 쇼핑몰의 고객지원센터 상담사야.")
                .advisors(spec -> spec
                        .param(ChatMemory.CONVERSATION_ID, user))
                .user(message)
                .tools(productOrderTool)
                .toolContext(Map.of("username", user))
                .stream().content().map(objectMapper::writeValueAsString);
    }

}
