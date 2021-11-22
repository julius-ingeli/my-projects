from digi.xbee.devices import XBeeDevice, XBee64BitAddress, RemoteXBeeDevice, XBeeNetwork
import time
from digi.xbee.models.status import NetworkDiscoveryStatus




device = XBeeDevice("COM5", 9600)

class discovery():

    def __init__(self):
        print("Opening serial connection to the device...")
    
    def callback_1(remote):
        print("Device discovered: %s" % remote)

    
    def callback_2(status):
        if status == NetworkDiscoveryStatus.SUCCESS:
            print("Discovery finished successfully!")
        else:
            print("There was an error discovering devices: %s" % status.description)
           


    def main(self):
        self.device = XBeeDevice("COM5", 9600)
        self.device = device
        time.sleep(0.5)
        self.device.open()
        print('Serial connection open.')

        xnet = self.device.get_network()
        xnet.set_discovery_timeout(5)
        xnet.clear()
        xnet.add_device_discovered_callback(callback=discovery.callback_1)
        xnet.add_discovery_process_finished_callback(callback=discovery.callback_2)

        xnet.start_discovery_process()
        print("Discovering modules on the network...")
        while xnet.is_discovery_running():
            time.sleep(0.5)

        

        discNodes = xnet.get_devices()
        hasNodes = xnet.has_devices()
        numNodes = xnet.get_number_devices()

    
        if hasNodes == False:
            print("No nodes found on the network.")
            exit()
        else:
            if numNodes == 1:
                print("Found", numNodes, "node.")
                for node in discNodes:
                    nodeString = str((node))
                    nodeInfo = nodeString.split(" - ")
                    nodeId = nodeInfo[1]
                    self.nodeMac = nodeInfo[0]
                    print("Node ID:", nodeId, "\nNode MAC Address:",self.nodeMac, "\n")
                    return self.nodeMac  
            else:
                print("Found", numNodes, "nodes.")
                for nodes in discNodes:
                    nodeString = str((nodes))
                    nodeInfo = nodeString.split(" - ")
                    nodeId = nodeInfo[1]
                    self.nodeMac = nodeInfo[0]
                    print("Node ID:", nodeId, "\nNode MAC Address:",self.nodeMac, "\n")
                    return self.nodeMac
