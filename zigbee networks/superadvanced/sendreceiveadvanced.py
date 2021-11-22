from digi.xbee.devices import XBeeDevice, XBee64BitAddress, RemoteXBeeDevice, XBeeNetwork
import time



class messaging():

    def __init__(self):
        print("Initializing messaging service...")


    
    
    
    def send(self, nodeMac,device):
  
        self.remote = RemoteXBeeDevice(device, XBee64BitAddress.from_hex_string(nodeMac))
        try:
            device.send_data(self.remote, "message from Coordinator.")
            print("Message sent successfully.")
            time.sleep(2)
        except(KeyboardInterrupt):
            print("Exiting sending mode...")
            
    

        
    """
    def receive(self, nodeMac, device):

        self.remote = RemoteXBeeDevice(device, XBee64BitAddress.from_hex_string(nodeMac))
        while True:
            xbee_message = device.read_data_from(self.remote)
            if xbee_message is not None:
                mesg = xbee_message.data
                message = mesg.decode()
                print(message)
            else:
                continue
    """

    def callback1(xbee_message):
        print("From %s >> %s" % (xbee_message.remote_device.get_64bit_addr(),xbee_message.data.decode()))


    def receive(self, nodeMac, device):
        device.add_data_received_callback(callback=callback1)











