#ifndef COMMAND_H
#define COMMAND_H

#include <string>
using namespace std;

#include "gameInfo.h"

class Command
{
public:
  string getConnectCommand(string clientName);
  // string getStartGameCommand(GameInfo gameInfo);

  class Keys
  {
  public:
    static const string CLIENT_NAME;
    static const string CLIENT_TYPE;
    static const string CLIENT_TOKEN;
    static const string COMM_TYPE;
    static const string GAME_NAME;
    static const string NUM_PLAYERS;
    static const string CONNECT;
    static const string GAME;
    static const string CREATE_GAME;
    static const string JOIN_GAME;
  };
};

#endif
