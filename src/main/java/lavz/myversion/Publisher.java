package lavz.myversion;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Publisher {

    private static final String EXCHANGE_NAME = "direct_lavz_exchange";
    private static final String ROUTING_KEY = "info";  // Define a routing key

    public static void main(String[] args) throws Exception {
        // Create a connection factory and configure it
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Or wherever your RabbitMQ is running
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare a fanout exchange
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            // Send a persistent message
            String message = "Hello, this is a direct exchange message!";
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "' with routing key '" + ROUTING_KEY + "'");
        }
    }

}
