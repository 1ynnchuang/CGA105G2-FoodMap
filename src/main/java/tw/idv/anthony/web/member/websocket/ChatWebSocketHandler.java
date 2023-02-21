package tw.idv.anthony.web.member.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {
	private Set<WebSocketSession> connectedSessionSet = Collections.synchronizedSet(new HashSet<>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		connectedSessionSet.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for (WebSocketSession connectedSession : connectedSessionSet) {
			if (connectedSession.isOpen()) {
				connectedSession.sendMessage(message);
			} else {
				connectedSessionSet.remove(connectedSession);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		connectedSessionSet.remove(session);
	}
}
