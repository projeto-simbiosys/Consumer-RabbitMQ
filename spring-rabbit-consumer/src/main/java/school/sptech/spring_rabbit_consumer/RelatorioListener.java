package school.sptech.spring_rabbit_consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private ObjectMapper objectMapper; // para converter JSON em objeto

    @Value("${app.destinatario.email}")
    private String emailDestinatario;

    @RabbitListener(queues = "${app.queue.name}")
    public void receberMensagem(String mensagemJson) {
        System.out.println("📨 Mensagem recebida: " + mensagemJson);

        try {
            // converte JSON para objeto
            RelatorioDto relatorio = objectMapper.readValue(mensagemJson, RelatorioDto.class);
            enviarEmailFormatado(relatorio);
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao converter mensagem: " + e.getMessage());
            // se falhar, envia e-mail com o JSON cru
            enviarEmailBruto(mensagemJson);
        }
    }

    private void enviarEmailFormatado(RelatorioDto relatorio) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDestinatario);
        email.setSubject("Novo relatório criado");

        String texto = String.format(
                "Um novo relatório foi criado!\n\n" +
                        "📅 Mês/Ano: %s\n" +
                        "🕓 Data de Atualização: %s\n" +
                        "✅ Aberto: %s\n\n" +
                        "ID: %d",
                relatorio.getMesAno(),
                relatorio.getDataAtualizacao(),
                relatorio.getAberto(),
                relatorio.getId()
        );

        email.setText(texto);
        mailSender.send(email);
        System.out.println("📧 E-mail enviado para " + emailDestinatario);
    }

    private void enviarEmailBruto(String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDestinatario);
        email.setSubject("Novo relatório criado");
        email.setText("Um novo relatório foi criado (dados brutos):\n\n" + mensagem);

        mailSender.send(email);
        System.out.println("📧 E-mail enviado com JSON cru para " + emailDestinatario);
    }
}


