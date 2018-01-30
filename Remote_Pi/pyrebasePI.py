import pyrebase

piID = 0        # Hardwired 

config = {
    "apiKey": "AIzaSyDsIYv8qSCaK5TqKVkpTmwsFG0o9Bp-JMI",
    "authDomain": "remote-e8cb4.firebaseapp.com",
    "databaseURL": "https://remote-e8cb4.firebaseio.com",
    "projectId": "remote-e8cb4",
    "storageBucket": "remote-e8cb4.appspot.com",
    "messagingSenderId": "137725497141",
    #"serviceAccount": "firebase-adminsdk-zd9v6@remote-e8cb4.iam.gserviceaccount.com.json"
}

firebase = pyrebase.initialize_app(config)
db = firebase.database()
#auth = firebase.auth()
#user = auth.sign_in_with_email_and_password("abhinav98jain@gmail.com", "iloveseins")
#db.child("users").child("Morty").set(data)
#while True:
lights = db.child("piID/"+str(piID)).get()
for i in lights.val():
    print ("Value of "+str(i)+"="+str(lights.val()[str(i)]))
