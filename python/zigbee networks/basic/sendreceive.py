from digi.xbee.devices import XBeeDevice, XBee64BitAddress, RemoteXBeeDevice, XBeeNetwork
import time
print("Initializing messaging service...")


class messaging():

    def __init__(self):
        print("Sending...")
    
    def send(self, nodeMac,device):



        self.remote = RemoteXBeeDevice(device, XBee64BitAddress.from_hex_string(nodeMac))
        while True:
            try:
                device.send_data(self.remote, "message from Coordinator.")
                print("Message sent successfully.")
                time.sleep(2)
            except(KeyboardInterrupt):
                print("Exiting sending mode...")
                break


    def receive(self, nodeMac, device):
        self.remote = RemoteXBeeDevice(device, XBee64BitAddress.from_hex_string(nodeMac))
        while True:
            try:
                xbee_message = device.read_data_from(self.remote)
                if xbee_message is not None:
                    mesg = xbee_message.data
                    message = mesg.decode()
                    print(message)
                else:
                    continue
            except(KeyboardInterrupt):
                print("Exiting receiving mode...")
                break






