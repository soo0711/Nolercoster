package com.Nolercoster.chatgpt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Nolercoster.chatgpt.entity.CompletionRequestEntity;

@Service
public interface ChatGPTService {
	 List<Map<String, Object>> modelList();

	 Map<String, Object> prompt(CompletionRequestEntity completionRequestDto);

	 Map<String, Object> isValidModel(String modelName);
}
