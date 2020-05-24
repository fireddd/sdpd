import time
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
try:
  import pyrebase
except:
  print("gg")

import pyrebase

config = {
	  "apiKey": "AIzaSyA9Dz39gJcAvzYVibKuy_ykjDMtSRiGGVc",
    "authDomain": "pythoncalling.firebaseapp.com",
    "databaseURL": "https://pythoncalling.firebaseio.com",
    "projectId": "pythoncalling",
    "storageBucket": "pythoncalling.appspot.com",
    "messagingSenderId": "871675086847"
}

firebase = pyrebase.initialize_app(config)

db = firebase.database()

storage=firebase.storage()

person_no=100

import cognitive_face as CF
from PIL import Image
import requests
from io import BytesIO

SUBSCRIPTION_KEY = 'c46850c1fd484e7ab7a12eb286cf77f0'
BASE_URL = 'https://centralindia.api.cognitive.microsoft.com/face/v1.0'
PERSON_GROUP_ID = 'consumers'
CF.BaseUrl.set(BASE_URL)
CF.Key.set(SUBSCRIPTION_KEY)
#CF.person_group.create(PERSON_GROUP_ID, 'consumers')
flag=1
while(flag):
    try:
        name = "Sundar Pichai"
        user_data = 'More information can go here'
        response = CF.person.create(PERSON_GROUP_ID, name, user_data)

        # Get person_id from response

        person_id = response['personId']
        image_url ='https://img.etimg.com/thumb/msid-56341879,width-300,imgsize-59748,resizemode-4/google-to-help-indian-small-and-medium-business-market-ride-the-digital-highway.jpg'
        CF.person.add_face(image_url, PERSON_GROUP_ID, person_id)

        CF.person_group.train(PERSON_GROUP_ID)

        #print (CF.person.lists(PERSON_GROUP_ID))

        response = CF.person_group.get_status(PERSON_GROUP_ID)
        status = response['status']
        if(status=="succeeded"):
            flag=0
    except:
        print("Try again")

face_api_url = 'https://centralindia.api.cognitive.microsoft.com/face/v1.0/detect'

import requests
from IPython.display import HTML

headers = {'Content-Type': 'application/octet-stream',
           'Ocp-Apim-Subscription-Key': SUBSCRIPTION_KEY}

params = {
    'returnFaceId': 'true',
    'returnFaceLandmarks': 'false',
    'returnFaceAttributes': 'age,gender,smile,emotion',
}

import os
from datetime import date
from datetime import datetime

os.chdir("face_results")
print(os.listdir())
def identify_person(test_url):
    response = CF.face.detect(test_url)
    image_data = open(r"person.jpg", "rb").read()
    #print("response is "+response)
    responses = requests.post(face_api_url, params=params, headers=headers,data=image_data)
    faces = responses.json()
    face_ids = [d['faceId'] for d in response]
    identified_faces = CF.face.identify(face_ids, PERSON_GROUP_ID)
    print(identified_faces)
    fa = faces[0]["faceAttributes"]
    gender=fa["gender"]
    age=fa["age"]
    age=str(age)
    finalemotion="GG"
    emotionneut=fa['emotion']['neutral']
    emotionhappy=fa['emotion']['happiness']
    emotionsad=fa['emotion']['sadness']
    if(emotionneut>0.5):
      finalemotion="Neutral"
    elif (emotionhappy>0.5):
      finalemotion="Happy"
    elif (emotionsad>0.5):
      finalemotion="Sad"
    else:
      finalemotion="Neutral"
    visiteddate=str(date.today())
    print(type(age),type(gender),type((finalemotion)),type(visiteddate))

    #theurstorage.child("images/newimage0.png").get_url(None)
    #Assuming that only one person comes in front of the camera for the first time

    for f in identified_faces:
      if not f['candidates']:

        #Create a new person for the unidentified person
        global person_no
        name='person'+str(person_no)
        person_no+=1
        status="failed"
        while(status!='succeeded'):
          response=CF.person.create(PERSON_GROUP_ID,name)
          person_id=response['personId']
          dbperson_id=str(person_id)
          print(person_id)
          storage.child("images/"+dbperson_id+".jpg").put("person.jpg")
          db.child("Data").child(dbperson_id).update({"Gender":gender})
          db.child("Data").child(dbperson_id).update({"Age":age})
          db.child("Data").child(dbperson_id).update({"Emotion":finalemotion})
          db.child("Data").child(dbperson_id).update({"Date":visiteddate})
          db.child("Data").child(dbperson_id).update({"Freq":"1"})
          db.child("Url").child("userid").update({"ID":dbperson_id})
          urlinfo=storage.child("images/"+dbperson_id+".jpg").get_url(NotImplemented)
          db.child("Urlinfo").child(dbperson_id).update({"url":urlinfo})
          CF.person.add_face(test_url,PERSON_GROUP_ID,person_id)
          CF.person_group.train(PERSON_GROUP_ID)
          response = CF.person_group.get_status(PERSON_GROUP_ID)
          status = response['status']
          print("Created new person : "+status)
      else:
        #print("Else is working")
        #print(f)
        #print(f['candidates'])
        print(f['candidates'][0]['personId']+"is updated")
        storage.child("images/"+str(f['candidates'][0]['personId'])+".jpg").put("person.jpg")
        urlinfo=storage.child("images/"+str(f['candidates'][0]['personId'])+".jpg").get_url(NotImplemented)
        db.child("Data").child(str(f['candidates'][0]['personId'])).update({"Gender":gender})
        db.child("Data").child(str(f['candidates'][0]['personId'])).update({"Age":age})
        db.child("Data").child(str(f['candidates'][0]['personId'])).update({"Emotion":finalemotion})
        db.child("Data").child(str(f['candidates'][0]['personId'])).update({"Date":visiteddate})
        db.child("Urlinfo").child(str(f['candidates'][0]['personId'])).update({"url":urlinfo})
        x=db.child("Data").child(str(f['candidates'][0]['personId'])).get().val()
        updatedfreq=int(x['Freq'])+1
        updatedfreq2=str(updatedfreq)
        db.child("Data").child(str(f['candidates'][0]['personId'])).update({"Freq":updatedfreq2})
        db.child("Url").child("userid").update({"ID":str(f['candidates'][0]['personId'])})

#print(len(os.listdir()))
while(1):
    if(len(os.listdir())==1):
        test_url="person.jpg"
        print("Run the function")
        flag2=1
        while(flag2):
            try:
                identify_person(test_url)
                flag2=0
            except:
                print("Try again")
        os.remove("person.jpg")
    else:
        print("nothing is there")
        time.sleep(1)
        break

print("It's over")
