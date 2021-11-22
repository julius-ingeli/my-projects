from digi.xbee.devices import XBeeDevice, XBee64BitAddress, RemoteXBeeDevice, XBeeNetwork
import time
import discover
import sendreceive

device = XBeeDevice("COM5", 9600)

disc=discover.discovery()
disc.main()

disc.nodeMac
coms = sendreceive.messaging()

while True:
    try:
        a = input("Select mode:\n1 - send\n2 - receive\nexit - exit\n")
        if a == "1":
            print("Entering send mode...")
            try:
                coms.send(disc.nodeMac, disc.device)
            except(KeyboardInterrupt):
                continue
        if a == "2":
            print("Entering receive mode...")
            try:
                coms.receive(disc.nodeMac, disc.device)
            except(KeyboardInterrupt):
                continue
        if a == "exit":
            print("exiting...")
            break
        if a == "help":
            continue
    except(KeyboardInterrupt):
        print("exiting...")
        exit()