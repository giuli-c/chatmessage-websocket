package com.criptologin.chatmessagewebsocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import dto.Message;
import dto.MessageOutput;

@Controller
public class MessageController {

	@MessageMapping("/input")
	@SendTo("/topic/output")
	public MessageOutput messages(Message output) throws Exception {
		Thread.sleep(1000);
		return new MessageOutput(HtmlUtils.htmlEscape(output.getName() + ": " + output.getTextMex()));
	}
}
