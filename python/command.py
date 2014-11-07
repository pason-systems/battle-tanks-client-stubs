import json
import gameinfo

class Command(object):
    """
    creates json commands that can be sent to the server
    """
    CLIENT_TOKEN = 'client_token'
    COMM_TYPE = 'comm_type'
    NUM_PLAYERS = 'num_players'
    TEAM_NAME = 'team_name'
    MATCH_TOKEN = 'match_token'
    PASSWORD = 'password'
    GAME_MOVE = 'GameMove'
    GAME_END = 'GameEnd'
    MATCH_END = 'MatchEnd'
    
    def getMatchConnectCommand(self, team_name, match_token, team_password):
        """
        returns a json command to connect to an established match.
        """
        dict = {}
        dict[Command.COMM_TYPE] = CommType.MATCH_CONNECT
        dict[Command.TEAM_NAME] = team_name
        dict[Command.MATCH_TOKEN] = match_token
        dict[Command.PASSWORD] = team_password
        return json.dumps(dict)

class CommType(object):
    MATCH_CONNECT = 'MatchConnect'
