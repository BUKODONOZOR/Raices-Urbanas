package Contest.Project.chat;

import Contest.Project.services.MessageService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.sql.Timestamp;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final MessageService messageService;

    // Constructor que recibe el MessageService
    public ChatWebSocketHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Aquí extraemos la información necesaria del mensaje recibido
        String messageBody = message.getPayload();
        int senderId = 8; // ID del remitente (puedes modificarlo si es necesario)
        int recipientId = 9; // ID del destinatario (puedes modificarlo si es necesario)
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Guardamos el mensaje (solo lo acumulamos, no lo guardamos aún en la base de datos)
        messageService.saveMessage(messageBody, senderId, recipientId, timestamp);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Guardamos el transcript completo cuando se cierra la conexión
        messageService.saveTranscript();
    }
}
