package com.Nolercoster.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	
	// Test1
	@ResponseBody
	@RequestMapping("/test1")
	public String test1() {
		return "Hello";
	}
	
	// Test2
	@ResponseBody
	@RequestMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<>();
		
		result.put("aaa", 111);
		result.put("bbb", 111);
		result.put("ccc", 111);
		
		return result;
	}
	
	// Test3
	@RequestMapping("/test3")
	public String test3() {
		return "test/test";
	}
}
