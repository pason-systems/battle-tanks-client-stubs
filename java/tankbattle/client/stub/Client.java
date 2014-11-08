package tankbattle.client.stub;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeromq.ZMQ;

final class Client
{
	class Type
	{
		public static final String CREATE = "create";
		public static final String JOIN = "join";
	}
	

	public static void main(String[] args)
	{
		Client.run(args);
	}

	public static void run(String[] args)
	{
		String ipAddress = null;
		String teamName = null;
		String password = null;
		String matchToken = null;

		if(args.length != 4) {
			Client.printHelp();
			return;
		}

		ipAddress = args[0];
		teamName = args[1];
		password = args[2];
		matchToken = args[3];

		System.out.println("Starting Battle Tank Client...");

		Command command = new Command();

		// retrieve the command to connect to the server
		String connectCommand = command.getMatchConnectCommand(teamName, password, matchToken);

		// retrieve the communication singleton
		Communication comm = Communication.getInstance(ipAddress, matchToken);

		// send the command to connect to the server
		System.out.println("Connecting to server...");
		String clientToken = comm.send(connectCommand, Command.Key.CLIENT_TOKEN);
		System.out.println("Received client token... " + clientToken);
		
		// Check to make sure we are connected
		if (null == clientToken)
		{
			System.out.println("Error: unable to connect!");
			System.exit(-1);
		}

		// the GameInfo object will hold the client's name, token, game type, etc.
		GameInfo gameInfo = new GameInfo(clientToken, teamName);

		// We are now connected to the server. Let's do some stuff:
		System.out.println("Connected!");
		
		System.out.println("Waiting for initial game state...");
		
		JSONObject gameState = comm.getJSONGameState(); // Blocking wait for game state example
		
		System.out.println("Received game state!");
		
		// Add your algorithm here
		System.out.println("Missing algorithm.");
		
		System.out.println("Exiting...");
	}

	public static void printHelp()
	{
			System.out.println("usage: Client <ip address> <team-name> <password> <match-token>");
	}
}
