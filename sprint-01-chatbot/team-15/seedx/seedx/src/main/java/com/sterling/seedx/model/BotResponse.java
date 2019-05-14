package com.sterling.seedx.model;

import java.util.ArrayList;
import java.util.List;

public class BotResponse {

	private List<Reply> replies;
	private Conversation conversation;
	public BotResponse() {
		replies = new ArrayList<Reply>();		
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	
}
