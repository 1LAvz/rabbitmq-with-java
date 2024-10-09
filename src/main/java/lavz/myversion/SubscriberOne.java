package lavz.myversion;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class SubscriberOne {

    private static final String EXCHANGE_NAME = "direct_lavz_exchange";
    private static final String QUEUE_NAME = "lavz_queue";
    private static final String ROUTING_KEY = "info";  // Use the same routing key

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declare a fanout exchange
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // Declare a durable queue (it will survive broker restarts)
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        System.out.println(" [*] Waiting for messages in queue: " + QUEUE_NAME);

        // Receive messages from the queue
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
