#ifndef COMMUNICATION_H
#define COMMUNICATION_H

#include <zmq.hpp>

class Communication
{
private:
  static const string PUB_SOCKET_ADDR;
  static const string CMD_SOCKET_ADDR;

  zmq::context_t context;
  zmq::socket_t pub_socket;
  zmq::socket_t cmd_socket;

public:
  enum MessageOrigin {
    PublishSocket = 0,
    CommandSocket
  };

  Communication();

  string send(string msg);
  string send(string msg, string key);
  string receive(MessageOrigin origin);
};

#endif