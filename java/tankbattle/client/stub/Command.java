package tankbattle.client.stub;

import org.json.JSONObject;
import org.json.JSONException;

final class Command
{
	class Key {
		private static final String TEAM_NAME = "team_name";
		private static final String PASSWORD = "password";
		public static final String CLIENT_TOKEN = "client_token";
		public static final String MESSAGE = "message";
		public static final String MSG = "msg";
		public static final String RESP = "resp";
		private static final String COMM_TYPE = "comm_type";
		private static final String MATCH_TOKEN = "match_token";

		class CommType {
			private static final String MATCH_CONNECT = "MatchConnect";
		}
	}

	public String getMatchConnectCommand(String teamName, String password, String matchToken)
	{
		JSONObject json = new JSONObject();

		try {
			json.put(Key.COMM_TYPE, Key.CommType.MATCH_CONNECT);
			json.put(Key.TEAM_NAME, teamName);
			json.put(Key.PASSWORD, password);
			json.put(Key.MATCH_TOKEN, matchToken);
		} catch(JSONException e) {
			System.err.println("[Command connectCommand] couldn't create command");
			return null;
		}

		return json.toString();
	}
	

}

