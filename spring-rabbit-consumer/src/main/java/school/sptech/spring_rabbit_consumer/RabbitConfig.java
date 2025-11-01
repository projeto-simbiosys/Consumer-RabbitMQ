package school.sptech.spring_rabbit_consumer;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue relatorioQueue() {
        return new Queue("relatorioQueue", true);
    }
}