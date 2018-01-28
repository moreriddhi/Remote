#authenticate from web and get piID 
from firebase import firebase
piID=0

firebase = firebase.FirebaseApplication('https://remote-e8cb4.firebaseio.com', None)
lightRef = firebase.ref("/piID/"+str(piID))
numberOfLights = (lightRef.get('/lights',None))    
for i in range(numberOfLights):
    light = "light" + str(i)
    result = firebase.get('/piID/0/'+light,None)
    print result 
