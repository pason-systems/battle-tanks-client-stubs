class GameInfo(object):
    """
    holds player information for the current game
    """

    def __init__(self, team_name, match_token, team_password, client_token = ''):
        self.client_token = client_token
        self.team_name = team_name
        self.match_token = match_token
        self.team_password = team_password