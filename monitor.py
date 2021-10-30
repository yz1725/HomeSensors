import requests
import json
from time import gmtime, strftime
from smbus2 import SMBus
from mlx90614 import MLX90614

bus = SMBus(1)
sensor = MLX90614(bus, address=0x5A)
targetTemp = sensor.get_object_1()
ambientTemp = sensor.get_ambient()

print "A temp:", ambientTemp
print "T temp:" targetTemp
bus.close()

currentTime= strftime('%Y-%m-%d %H:%M:%S', gmtime())
print "time collected", currentTime
url = 'http://34.127.33.28:8080/temperature/create'
payload= {'target':targetTemp, 'ambient':ambientTemp, 'timeCollected':currentTime}
headers={'Content-Type':'application/json'}

r = requests.post(url, data=json.dumps(payload), headers=headers)
print r
