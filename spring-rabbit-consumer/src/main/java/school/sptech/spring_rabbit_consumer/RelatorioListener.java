package school.sptech.spring_rabbit_consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RelatorioListener {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.destinatario.email}")
    private String emailDestinatario;

    // O Spring já converte JSON em RelatorioDto automaticamente
    @RabbitListener(queues = "${app.queue.name}")
    public void receberMensagem(RelatorioDto relatorio) {
        System.out.println("📨 Mensagem recebida: " + relatorio);
        enviarEmailFormatado(relatorio);
    }

    private void enviarEmailFormatado(RelatorioDto relatorio) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDestinatario);
        email.setSubject("Novo relatório criado");

        String texto = String.format(
                """
                📝 Um novo relatório foi criado!

                📅 Mês/Ano: %s
                🕓 Data de Atualização: %s
                ✅ Aberto: %s
                👤 Usuário: %s
                🆔 ID: %d
                """,
                relatorio.getMesAno(),
                relatorio.getDataAtualizacao(),
                relatorio.getAberto() ? "Sim" : "Não",
                relatorio.getUsuarioNome() != null ? relatorio.getUsuarioNome() : "Não informado",
                relatorio.getId()
        );

        email.setText(texto);
        mailSender.send(email);
        System.out.println("📧 E-mail enviado para " + emailDestinatario);
    }
}



