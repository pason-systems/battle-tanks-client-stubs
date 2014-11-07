#ifndef GAME_INFO_H
#define GAME_INFO_H

#include <string>
using namespace std;

class GameInfo
{
public:
  enum GameType {
    Unknown = 0,
    Create,
    Join
  };

  GameInfo(string clientToken);

  static const string CLIENT_NAME;
  static const string MATCH_TOKEN;
  static const string CLIENT_PWD;

  string getClientToken();
  string getClientName();
  string getClientPwd();
  string getHostname();

private:
  string _clientName;
  string _client_pwd;
  string _clientToken;
};

#endif
