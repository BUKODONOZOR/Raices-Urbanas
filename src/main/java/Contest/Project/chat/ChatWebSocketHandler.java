package Contest.Project.chat;

import Contest.Project.services.MessageService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final MessageService messageService;
    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    // Constructor que recibe el MessageService
    public ChatWebSocketHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Agregar la sesión cuando un usuario se conecta
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Extraemos la información necesaria del mensaje recibido
        String messageBody = message.getPayload();
        int senderId = 8; // ID del remitente (puedes modificarlo si es necesario)
        int recipientId = 9; // ID del destinatario (puedes modificarlo si es necesario)
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Acumulamos el mensaje (sin guardarlo aún en la base de datos)
        messageService.saveMessage(messageBody, senderId, recipientId, timestamp);

        // Difundir el mensaje a todas las sesiones activas
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(messageBody));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remover la sesión cuando un usuario se desconecta
        sessions.remove(session);

        // Guardar el transcript completo cuando se cierra la conexión
        messageService.saveTranscript();
    }
}