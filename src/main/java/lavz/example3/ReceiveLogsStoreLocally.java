package lavz.example3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiveLogsStoreLocally {

    private static final String EXCHANGE_NAME = "logs";
    private static final String FILE_PATH = "/home/avanzi/Downloads/logfile.txt";


    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            writeLogIntoAFile(message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

    private static void writeLogIntoAFile(String message) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(message);
            System.out.printf("Log was successfully stored in %s", FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
