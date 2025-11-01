package school.sptech.spring_rabbit_consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RelatorioConsumer {

    private final JavaMailSender mailSender;

    @Value("${app.destinatario.email}")
    private String destinatario;

    public RelatorioConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "${app.queue.name}")
    public void receberMensagem(String mensagem) {
        System.out.println("📨 Mensagem recebida: " + mensagem);
        enviarEmail(mensagem);
    }

    private void enviarEmail(String mensagem) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(destinatario);
        mail.setSubject("Novo Relatório Criado");
        mail.setText("Um novo relatório foi criado:\n\n" + mensagem);
        mailSender.send(mail);

        System.out.println("📧 E-mail enviado para: " + destinatario);
    }
}

