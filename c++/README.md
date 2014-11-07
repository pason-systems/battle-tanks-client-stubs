# C++ Battle Tetris Sample Client

## Dependencies

### 1. ZeroMQ

[ZeroMQ](http://www.zeromq.org/)

### 2. ZeroMQ C++ Binding

[ZeroMQ C++ Binding](http://www.zeromq.org/bindings:cpp)

## Running the Client
    1. `make`
    2. `./client.app`

## Server Settings

In `communication.cpp`:  
- change `PUB_SOCKET_ADDR` to point to the server's publish/subscribe socket  
- change `CMD_SOCKET_ADDR` to point to the server's request/reply socket  

## Student Settings

In `gameInfo.cpp`:  
- change `CLIENT_NAME` to your client name  
- change `MATCH_TOKEN` to your match token
- change `CLIENT_PWD` to your password
