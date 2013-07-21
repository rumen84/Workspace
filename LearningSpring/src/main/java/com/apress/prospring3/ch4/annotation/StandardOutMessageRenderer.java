package com.apress.prospring3.ch4.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer {

	private MessageProvider messageProvider = null;
		
	@Override
	public void render() {
		if (messageProvider == null) {
			throw new RuntimeException(
					"You must set the property messageProvider of class:"
							+ StandardOutMessageRenderer.class.getName());
		}
		System.out.println(messageProvider.getMessage());
	}

	@Autowired
	@Override
	public void setMessageProvider(MessageProvider provider) {
		this.messageProvider = provider;		
	}

	@Override
	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

}
