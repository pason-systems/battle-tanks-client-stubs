#include "gameInfo.h"

const string GameInfo::CLIENT_NAME = "autop2_1415384712";
const string GameInfo::MATCH_TOKEN = "875496d7-3a2d-4444-be82-7ebe35e08cdd";
const string GameInfo::CLIENT_PWD = "autop2_1415384712";

GameInfo::GameInfo(string clientToken)
{
  _clientToken = clientToken;
  _clientName = CLIENT_NAME;
  _client_pwd = CLIENT_PWD;
}

string GameInfo::getClientToken()
{
  return _clientToken;
}

string GameInfo::getClientName()
{
  return _clientName;
}

string GameInfo::getClientPwd()
{
  return _client_pwd;
}
