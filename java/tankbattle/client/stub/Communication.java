package tankbattle.client.stub;

import org.zeromq.ZMQ;
import org.json.JSONObject;
import org.json.JSONException;

final class Communication
{
	enum MessageOrigin {
		PublishSocket,
		CommandSocket
	};

	private static Communication instance = null;

	private static final String PUB_SOCKET_PORT = "5556";
	private static final String CMD_SOCKET_PORT = "5557";

	private ZMQ.Context context;
	private ZMQ.Socket pubSocket;
	private ZMQ.Socket cmdSocket;
	private String pubSocketAddress;
	private String cmdSocketAddress;
	private String matchToken;

	private Communication()
	{

	}

	private Communication(String pubSocketAddress, String cmdSocketAddress, String matchToken)
	{
		this.matchToken = matchToken;
		
		context = ZMQ.context(1);
		pubSocket = context.socket(ZMQ.SUB);
		cmdSocket = context.socket(ZMQ.REQ);

		pubSocket.connect(pubSocketAddress);
		cmdSocket.connect(cmdSocketAddress);

    // This will cause our client to receive published messages
    // from the server concerning all of the games happening
    // on the server, including games from other clients in
    // other matches.
    // To receive only the messages for this match, use the
    // match token as the 2nd argument instead of ''
		pubSocket.subscribe(matchToken.getBytes());
	}

	public static Communication getInstance(String ipAddress, String matchtoken)
	{
		if(instance == null) {
			System.out.println("Creating zeromq connections...");
			instance = new Communication("tcp://" + ipAddress + ":" + PUB_SOCKET_PORT, "tcp://" + ipAddress + ":" + CMD_SOCKET_PORT, matchtoken);
		}

		return instance;
	}

	public String send(String message)
	{
		byte[] bytes = message.getBytes();
		cmdSocket.send(bytes, 0);
		

		// wait for reply
		return receive(Communication.MessageOrigin.CommandSocket);
	}

	public String send(String message, String key)
	{
		
		//System.out.println("Sending message: " + message);
		String reply = send(message);
		JSONObject json = null;
		String value = null;

		try {
			json = new JSONObject(reply);
		//	System.out.println("Recieved Reply: " + json);
		} catch(JSONException e) {
			System.err.println("[Communication send] couldn't create JSONObject from reply");
			return null;
		}
		
		try {
			value = json.getString(key);
		} catch(JSONException e) {
			System.err.println("[Communication send] couldn't extract value for key: " + key + " from reply");
			return null;
		}

		return value;
	}
	
	public String sendCMD(String message, String key)
	{
		
		//System.out.println("Sending message: " + message);
		String reply = send(message);
		JSONObject json = null;
		String value = null;

		try {
			json = new JSONObject(reply);
		//	System.out.println("Recieved Reply: " + json);
		} catch(JSONException e) {
			System.err.println("[Communication send] couldn't create JSONObject from reply");
			return null;
		}
		
		try {
			value = json.getJSONObject(key).getString(key);
		} catch(JSONException e) {
			System.err.println("[Communication send] couldn't extract value for key: " + key + " from reply");
			return null;
		}

		return value;
	}

	public String receive(MessageOrigin origin)
	{
		byte[] bytes = null;
		switch(origin) {
			case PublishSocket:
				bytes = pubSocket.recv(0);
				break;
			case CommandSocket:
				bytes = cmdSocket.recv(0);
				break;
			default:
				break;
		}

		return new String(bytes);
	}
	
	public JSONObject getJSONGameState()
	{
				
		String gameState = new String();
		if(this.receive(Communication.MessageOrigin.PublishSocket).equals(this.matchToken))
		{
			gameState = this.receive(Communication.MessageOrigin.PublishSocket);
		
			
			JSONObject json = null;
		
			try {
				json = new JSONObject(gameState);
			} catch(JSONException e) {
				System.err.println("[Communication recieve] couldn't create JSONObject from gamestate");
				return null;
			}
			
			return json;
		}
		else
		{
			System.err.println("[Communication recieve] recieved gamestate for incorrect matchtoken");
			System.exit(-1);
			return null;
		}
	}
	
}