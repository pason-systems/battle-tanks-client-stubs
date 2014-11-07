import zmq
import json
import command

class Communication(object):
    """
    handles communication to/from the server
    """

    def __init__(self, host_name):
        """
        initializes Communication object
        """
        pub_socket_addr = "tcp://%s:%s"%(host_name, 5556)
        cmd_socket_addr = "tcp://%s:%s"%(host_name, 5557)

        self.context = zmq.Context()
        self.pub_socket = self.context.socket(zmq.SUB)
        self.pub_socket.connect(pub_socket_addr)

        self.cmd_socket = self.context.socket(zmq.REQ)
        self.cmd_socket.connect(cmd_socket_addr)

    def set_subscription(self, value):
        """
        sets the subscription for the publish socket
        otherwise it will receive no messages on the
        pub/sub channel
        """
        self.pub_socket.setsockopt(zmq.SUBSCRIBE, value)

    def send(self, message, key=None):
        """
        sends 'message' to the servers command socket
        extracts and returns 'key' from the servers reply
        if no 'key' specified, returns the entire reply
        """
        reply = None
        self.cmd_socket.send(message)
        reply = self.receive(Communication.Origin.CommandSocket)

        if key == None:
            return reply
        else:
            return json.loads(reply)[key]

    def receive(self, origin):
        """
        returns a message from the socket specified by 'origin'
        """
        reply = None
        if origin == Communication.Origin.PublishSocket:
            reply = self.pub_socket.recv()
        elif origin == Communication.Origin.CommandSocket:
            reply = self.cmd_socket.recv()
        else:
            print '[Communication receive] wrong origin specified'
            exit(1)

        return reply

    def match_ended(self):
        while True:
            try:
                reply = self.pub_socket.recv(zmq.NOBLOCK)
                try:
                    if(json.loads(reply)[command.Command.COMM_TYPE] == command.Command.MATCH_END):
                        return True
                except ValueError:
                    continue
            except zmq.ZMQError:
                break

        return False

    def close(self):
        """
        close connections
        """
        self.cmd_socket.close()
        self.pub_socket.close()

    class Origin:
        PublishSocket = 0
        CommandSocket = 1