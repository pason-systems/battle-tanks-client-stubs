#include <iostream>
using namespace std;

#include "gameInfo.h"
#include "command.h"
#include "cJSON.h"

const string Command::Keys::CLIENT_NAME = "client_name";
const string Command::Keys::CLIENT_TOKEN = "client_token";
const string Command::Keys::COMM_TYPE = "comm_type";

string Command::getConnectCommand(string clientName)
{
  cJSON *root = cJSON_CreateObject();
  cJSON_AddItemToObject(root, "comm_type", cJSON_CreateString("MatchConnect"));
  cJSON_AddItemToObject(root, "team_name", cJSON_CreateString(clientName.c_str()));
  cJSON_AddItemToObject(root, "match_token", cJSON_CreateString(GameInfo::MATCH_TOKEN.c_str()));
  cJSON_AddItemToObject(root, "password", cJSON_CreateString(GameInfo::CLIENT_PWD.c_str()));
  return cJSON_PrintUnformatted(root);
}
