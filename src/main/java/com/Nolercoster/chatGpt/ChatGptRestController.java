package com.Nolercoster.chatGpt;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Nolercoster.chatGpt.bo.ChatGptBO;
import com.Nolercoster.chatGpt.domain.ChatGpt;


@RestController
@RequestMapping(value = "/api/v1/chatGpt")
public class ChatGptRestController {

	@PostMapping("/prompt")
    public ResponseEntity<Map<String, Object>> selectPrompt(@RequestBody ChatGpt chatGpt) {
        Map<String, Object> result = ChatGptBO.prompt(chatGpt);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
