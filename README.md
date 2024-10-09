# Prerequisites
> 1. Need RabbitMQ installed and running on localhost on the standard port (5672). In case you use a different host, port or credentials, connections settings would require adjusting.   
> 2. Also need to install docker.

### Run the following to start a local docker image for rabbitmq:
> docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3

* Once the container is up and running, you can create queues;
* To consult a queue you've created, open the terminal on you local machine and then execute: <strong>docker exec -it some-rabbit /bin/bash</strong>. The terminal on you rabbitmq container will be executed, allowing you to consult its information.
* List the created queues: <strong>rabbitmqctl list_queues</strong>

### Folder structure in the project
#### package 'example1'
> **"Hello World!"**  
> It contains two classes: Recv and Send.  
> - **Send** is responsible for connecting to a queue or create one if it does not exist yet. It sends a message and then close the connections.   
> - **Recv** connect and listen to the queue, this service continues running unless explicit stopped.   
> - https://www.rabbitmq.com/tutorials/tutorial-one-java

#### package 'example2'
> **Work Queues**   
> It contains two classes: NewTask and Worker.
> - **NewTask** add tasks to the queue.
> - **Worker** consume tasks from the queue. You can start multiple works to handle messages(taks) more quickly.   
> - https://www.rabbitmq.com/tutorials/tutorial-two-java

#### package 'example3'
> **Publish/Subscribe**   
> It contains three classes: EmitLog, ReceiveLogsPrintConsole, ReceiveLogsStoreLocally. The exchange type used is funout, which delivers messages to all queues bound to the exchange.
> - **EmitLog** send logs to the exchange.
> - **ReceiveLogsPrintConsole** This consumer is attached to a queue which is bound to a specific exchange. It writes log to the console when a message is delivered.
> - **ReceiveLogsStoreLocally** It writes log to a file in the disk when a message is delivered.
> - https://www.rabbitmq.com/tutorials/tutorial-three-java


 #### package 'example4'
> **Publish/Subscribe with direct exchange**   
> It contains three classes: EmitLogDirect, ReceiveLogsDirectToConsole and ReceiveLogsDirectToLocalFile. The exchange type used is direct,
> which delivers messages to all queues bound to the exchange and has a matching ROUTING KEY. So the producer must inform a routing key, and the queues can declare their
> interest in hearing out for message by declaring the same routing key.
> - **EmitLogDirect** send logs to the exchange.
> - **ReceiveLogsDirectToConsole** Any queues that are attached to a specific exchange will receive the log from the producer.
> - **ReceiveLogsDirectToLocalFile** Any queues that are attached to a specific exchange will receive the log from the producer.
> - https://www.rabbitmq.com/tutorials/tutorial-four-java


---
--
>> For better understanding on how rabbitmq works, consult its documentation on:
> https://www.rabbitmq.com/tutorials