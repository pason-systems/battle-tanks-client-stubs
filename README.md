# Battle Tanks Sample Clients

These clients all do the same thing. The only difference is the language.

**Read the README within each language's folder for specific instructions.**

These clients do the following:
* Accept a team name, password, match token, and game server hostname  
* Connect to the game server using ZeroMQ with the host name specified  
* Send a MatchConnect command using the team name, password, and match token specified  
* Receive the server's response to the MatchConnect command and parse the client token from it  

Within these clients you're shown how to:
* Connect to ZeroMQ  
* Create ZeroMQ sockets  
* Set a ZeroMQ subscription on a publish/subscribe socket  
* Create JSON messages  
* Send a message to the game server  
* Receive a message from the game server  
* Parse a JSON message  

What to do next:
* Start receiving and parsing game piece and board state messages from the publish/subscribe socket  
* Create an algorithm and send game moves back to the server  

Good luck!
