# Java Battle Tanks Sample Client

## Dependencies

### 1. ZeroMQ

[ZeroMQ](http://www.zeromq.org/)

### 2. ZeroMQ Java Binding

[ZeroMQ Java Binding](http://www.zeromq.org/bindings:java)

## Compiling the Client (from the same directory as this file)

### Ubuntu

javac -cp /usr/local/share/java/zmq.jar:. tankbattle/client/stub/Client.java

### Windows

javac -cp .;C:\zmq\java\lib\zmq.jar tankbattle\client\stub\Client.java

## Running the Client (from the same directory as this file)

### Ubuntu  

java -Djava.library.path=/usr/local/lib -cp /usr/local/share/java/zmq.jar:. tankbattle.client.stub.Client <ip-address> <team-name> <password> <match-token>

### Windows

java -Djava.library.path=C:\zmq\java\lib -cp .;C:\zmq\java\lib\zmq.jar tankbattle.client.stub.Client <ip-address> <team-name> <password> <match-token>

## Example output when stub is ran

Starting Battle Tank Client...
Creating zeromq connections...
Connecting to server...
Received client token... 48de8b34-c393-4b14-817a-6e07896525cb
Connected!
Waiting for initial game state...
Received game state!
Missing algorithm.
Exiting...
