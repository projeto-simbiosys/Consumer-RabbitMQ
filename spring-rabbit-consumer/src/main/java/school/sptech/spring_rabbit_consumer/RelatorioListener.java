package school.sptech.spring_rabbit_consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RelatorioListener {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.destinatario.email}")
    private String emailDestinatario;

    @RabbitListener(queues = "${app.queue.name}")
    public void receberMensagem(String mensagem) {
        System.out.println("📨 Mensagem recebida: " + mensagem);
        enviarEmail(mensagem);
    }

    private void enviarEmail(String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDestinatario);
        email.setSubject("Novo relatório criado");
        email.setText("Um novo relatório foi criado:\n\n" + mensagem);

        mailSender.send(email);
        System.out.println("📧 E-mail enviado para " + emailDestinatario);
    }
}

