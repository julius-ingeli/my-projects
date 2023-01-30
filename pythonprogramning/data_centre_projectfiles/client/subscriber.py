import paho.mqtt.client as mqtt
import time

def on_message (client, userdata, message):
    print("Recieved:", str(message.payload.decode("utf-8")))

mqttBroker = "teama2.eastus.cloudapp.azure.com"
client = mqtt.Client("Client 2")
client.connect(mqttBroker)

client.loop_start()
client.subscribe("Temperature")
client.on_message = on_message
time.sleep(10)
client.loop_stop()