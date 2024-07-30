package com.Nolercoster.chatGpt.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Nolercoster.chatGpt.domain.ChatGpt;
import com.Nolercoster.config.OpenAiConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatGptBO {
	
	private final OpenAiConfig chatGptConfig;
	
    public ChatGptBO(OpenAiConfig chatGptConfig) {
        this.chatGptConfig = chatGptConfig;
    }
    
    @Value("${openai.model}")
    private String model;
	
	
	public List<Map<String, Object>> prompt(ChatGpt chatGpt) {
        log.debug("[+] 프롬프트를 수행합니다.");

        List<Map<String, Object>> result = new ArrayList<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = chatGptConfig.httpHeaders();

        String requestBody = "";
        ObjectMapper om = new ObjectMapper();

        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
        chatGpt = chatGpt.builder()
                .model(model)
                .prompt(chatGpt.getPrompt())
                .temperature(0.8f)
                .build();

        try {
            // [STEP4] Object -> String 직렬화를 구성합니다.
            requestBody = om.writeValueAsString(chatGpt);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
        HttpEntity requestEntity = new HttpEntity<>(chatGpt, headers);
        ResponseEntity response = chatGptConfig.restTemplate()
                .exchange(
                        "<https://api.openai.com/v1/completions>",
                        HttpMethod.POST,
                        requestEntity,
                        String.class);
        try {
            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
            result = om.readValue(response.getBody(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
	}

}
