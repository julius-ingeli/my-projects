from digi.xbee.devices import XBeeDevice, XBee64BitAddress, RemoteXBeeDevice, XBeeNetwork
import time
import discoveradvanced
import sendreceiveadvanced


disc=discoveradvanced.discovery()
disc.main()
disc.nodeMac
disc.device

coms = sendreceiveadvanced.messaging()

remote = RemoteXBeeDevice(disc.device, XBee64BitAddress.from_hex_string(disc.nodeMac))

while True:
    try:
        a = input("Select mode:\n1 - send\n2 - receive\nexit - exit\n")
        if a == "1":
            print("Entering send mode...")
            print("Forcing other client to enter receive mode...")
            disc.device.send_data(remote, "yo_enter_receive_mode_yo")
            try:
                coms.send(disc.nodeMac, disc.device)
            except(KeyboardInterrupt):
                print("Exiting sending mode...")
                print("Forcing the remote client to exit receive mode...")
                disc.device.send_data(remote, "yo_exit_receive_mode_yo")
                continue
        if a == "2":
            print("Entering receive mode...")
            print("Forcing the other client to enter send mode...")
            disc.device.send_data(remote, "yo_enter_send_mode_yo")
            try:
                coms.receive(disc.nodeMac, disc.device)
            except(KeyboardInterrupt):
                print("Exiting receive mode...")
                print("Forcing the remote client to exit send mode...")
                disc.device.send_data(remote, "yo_exit_send_mode_yo")
                continue
        if a == "exit":
            print("Exiting...")
            disc.device.send_data(remote, "yo_exit_completely_yo")
            break
        if a == "help":
            continue
    except(KeyboardInterrupt):
        print("Exiting...")
        disc.device.send_data(remote, "yo_exit_completely_yo")
        exit()