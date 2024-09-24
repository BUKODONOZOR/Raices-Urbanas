package Contest.Project.services;

import Contest.Project.entities.Message;
import Contest.Project.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private StringBuilder transcriptBuilder = new StringBuilder(); // Variable para acumular los mensajes

    // Método para agregar mensajes al transcript
    public void saveMessage(String messageBody, int senderId, int recipientId, Timestamp timestamp) {
        // Acumulamos el mensaje en el StringBuilder
        transcriptBuilder.append("[").append(timestamp).append("] ");
        transcriptBuilder.append("Sender ID: ").append(senderId).append(", ");
        transcriptBuilder.append("Recipient ID: ").append(recipientId).append(" -> ");
        transcriptBuilder.append(messageBody).append("\n");
    }

    // Método para guardar el transcript completo en la base de datos
    public void saveTranscript() {
        // Creamos un nuevo mensaje con todo el transcript acumulado
        Message transcriptMessage = new Message();
        transcriptMessage.setBody(transcriptBuilder.toString()); // Guardamos el transcript en el campo body
        transcriptMessage.setDate(new Timestamp(System.currentTimeMillis()).toLocalDateTime()); // Guardamos la fecha actual
        transcriptMessage.setSender(8); // ID del remitente (puedes modificarlo si es necesario)
        transcriptMessage.setRecipient(9); // ID del destinatario (puedes modificarlo si es necesario)

        // Guardamos el transcript completo en la base de datos
        messageRepository.save(transcriptMessage);

        // Limpiamos el StringBuilder para el siguiente transcript
        transcriptBuilder.setLength(0);
    }
}
