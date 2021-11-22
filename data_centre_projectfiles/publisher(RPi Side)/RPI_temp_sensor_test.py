import paho.mqtt.client as mqtt
import RPi.GPIO as GPIO
import time
import Freenove_DHT as DHT
import json

DHTPin = 11

dht = DHT.DHT(DHTPin)   #create a DHT class object
sumCnt = 0

mqttBroker = "20.185.62.8"
client = mqtt.Client("Client 1")
client.connect(mqttBroker)
print("Connected to the MQTT broker.")
  
while True:
    try:
        sumCnt += 1         #counting number of reading times
        chk = dht.readDHT11()     #read DHT11 and get a return value. Then determine whether data read is normal according to the return value.
        print ("The sumCnt is : %d, \t chk    : %d"%(sumCnt,chk))
        if (chk is dht.DHTLIB_OK):      #read DHT11 and get a return value. Then determine whether data read is normal according to the return value.
            print("Temperature of", str(dht.temperature), "recorded.")
        elif(chk is dht.DHTLIB_ERROR_CHECKSUM): #data check has errors
            print("Error with data check! Data not sent.")
            time.sleep(2)
            sumCnt -= 1
            continue
        elif(chk is dht.DHTLIB_ERROR_TIMEOUT):  #reading DHT times out
            print("Reading from sensor timed out.")
            time.sleep(2)
            sumCnt -= 1
            continue
        else:               #other errors
            print("Other error!")
            time.sleep(2)
            sumCnt -= 1
            continue
            
        data = {
            "Name" : "Temperature reading",
            "ID" : sumCnt,
            "Temperature" : dht.temperature

        }
        client.publish("Temperature", json.dumps(data))
        print("Temperature of", str(dht.temperature), "published.")
        time.sleep(2)
    except(KeyboardInterrupt):
        client.disconnect(mqttBroker)
        print("Connection disconnected. ")
        GPIO.cleanup()
        break
    
