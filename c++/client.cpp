#include <iostream>
using namespace std;

#include "gameInfo.h"
#include "command.h"
#include "communication.h"

class Client
{
public:
  void run(int argc, char* argv[]);
};

void Client::run(int argc, char* argv[])
{
  cout << "Starting Battle Tanks Client..." << endl;

  Command command;
  string connectCmd = command.getConnectCommand(GameInfo::CLIENT_NAME);

  cout << "Connecting to server..." << endl;

  Communication comm;
  string clientToken = comm.send(connectCmd, Command::Keys::CLIENT_TOKEN);

  cout << "Received client token ..." << clientToken << endl;
  GameInfo gameInfo(clientToken);

  cout << "Starting game..." << endl;

  ///
  /// ADD ALGORITHM
  cout << "Missing algorithm..." << endl;
  ///

  cout << "Exiting..." << endl;
}

int main(int argc, char* argv[])
{
  Client client;
  client.run(argc, argv);
}
