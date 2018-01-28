#authenticate from web and get piID 
from firebase import firebase
piID=0

firebase = firebase.FirebaseApplication('https://remote-e8cb4.firebaseio.com', None)
numberOfLights = (firebase.get('/piID/'+str(piID)+'/lights',None))    
for i in range(numberOfLights-1):
    light = "light" + str(i)
    result = firebase.get('/piID/0/'+light,None)
    print result 
