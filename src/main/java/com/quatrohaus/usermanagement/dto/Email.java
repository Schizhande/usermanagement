package com.quatrohaus.usermanagement.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Email {

	private List<Attachment> attachments;
	private Body body;
	private List<EmailReceiptients> emailRecipients;
	private String from;
	private Subject subject;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Attachment {
		private String name;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Body {
		private String message;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class EmailReceiptients {
		private String emailAddress;
		private String type;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Subject {
		private String value;
	}
}
