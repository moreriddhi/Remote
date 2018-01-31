#authenticate from web and get piID 
from firebase import firebase
import wiringpi
piID=0

# pin numbers
light0 = 5
light1 = 6
light2 = 7
light3 = 8

firebase = firebase.FirebaseApplication('https://remote-e8cb4.firebaseio.com', None)
numberOfLights = firebase.get('/piID/'+str(piID)+'/lights',None)    
wiringpi.wiringPiSetup()
 for i in range(numberOfLights-1):
     light = "light" + str(i)
     wiringpi.pinMode(light, 1) 

while True:
    for i in range(numberOfLights-1):
        light = "light" + str(i)
        result = int(firebase.get('/piID/0/'+light,None))
        if light == 1:       
        print result 
