package tankbattle.client.stub;

final class GameInfo
{
	private String clientToken;
	private String teamName;

	GameInfo(String clientToken,
		String teamName)
	{
		this.clientToken = clientToken;
		this.teamName = teamName;
	}

	public String getClientToken()
	{
		return clientToken;
	}

	public String getTeamName()
	{
		return teamName;
	}
}