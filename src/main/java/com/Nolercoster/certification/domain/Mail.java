package com.Nolercoster.certification.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
	private String address;
	private String title;
	private String message;
}
