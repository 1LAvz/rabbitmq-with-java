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
> It contains two classes: Recv and Send.  
> - **Send** is responsible for connecting to a queue or create one if it does not exist yet. It sends a message and then close the connections.   
> - **Recv** connect and listen to the queue, this service continues running unless explicit stopped.


---
--
>> For better understanding on how rabbitmq works, consult its documentation on:
> https://www.rabbitmq.com/tutorials