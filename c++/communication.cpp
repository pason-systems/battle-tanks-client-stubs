#include <string>
#include <iostream>
using namespace std;

#include "communication.h"
#include "cJSON.h"

const string Communication::PUB_SOCKET_ADDR = "tcp://localhost:5556";
const string Communication::CMD_SOCKET_ADDR = "tcp://localhost:5557";

Communication::Communication() : 
context(1),
pub_socket(context, ZMQ_SUB),
cmd_socket(context, ZMQ_REQ)
{
  pub_socket.connect(PUB_SOCKET_ADDR.c_str());
  cmd_socket.connect(CMD_SOCKET_ADDR.c_str());
}

string Communication::send(string msg, string key)
{
  cJSON *value = NULL;
  string reply = send(msg);

  cJSON *json = cJSON_Parse(reply.c_str());
  value = cJSON_GetObjectItem(json, key.c_str());
  if(!value) {
    cout << "Couldn't extract client token from server response..." << endl;
    cout << "Exiting..." << endl;
    exit(1);
  }

  string str(value->valuestring);
  return str;
}

string Communication::send(string msg)
{
  zmq::message_t request(msg.length());
  memcpy(request.data(), msg.c_str(), msg.length());
  cmd_socket.send(request);

  return receive(CommandSocket);
}

string Communication::receive(MessageOrigin origin)
{
  zmq::message_t reply;

  switch(origin) {
    case PublishSocket:
      pub_socket.recv(&reply);
      break;

    case CommandSocket:
      cmd_socket.recv(&reply);
      break;

    default:
      break;
  }

  int size = reply.size();
  char *data = (char*)malloc(size+1);
  memcpy(data, reply.data(), size);
  string str(data);
  return str;
}