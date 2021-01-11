#!/usr/bin/env python
# coding: utf-8

# In[1]:

from keras.models import Model, load_model
import numpy as np
from keras.preprocessing import image
from scipy import spatial
from pathlib import Path
from tensorflow import keras
import cv2
import random
# In[12]:


model = keras.models.load_model('inference_model.h5')


# In[28]:


def preprocess(img1,img2):
    img1 = cv2.resize(img1,(224,224))
    img1 = img1.astype(np.float32)/255.

    img2 = cv2.resize(img2,(224,224))
    img2 = img2.astype(np.float32)/255.
  
    img1 = img1.reshape(-1,224,224,3)
    img2 = img2.reshape(-1,224,224,3)
    return img1,img2


# In[30]:
# def frontFace(photo):
#     face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

#     path = './static/{}'.format(photo)
#     img = cv2.imread(path)
#     gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

#     faces = face_cascade.detectMultiScale(img, 1.3, 5)
#     for (x,y,w,h) in faces:
#         cv2.rectangle(img,(x,y),(x+w,y+h),(0,255,255),2)

#         offset = 10
#         face_section = img[y-offset:y+h+offset,x-offset:x+w+offset]
#         face_section = cv2.resize(face_section,(224,224))
        
#     return face_section

def predict(photo):
    #p = Path("bollywood_celeb_faces_0/")
    p = Path("dataset111/") 
    dirs = p.glob("*")
    
    label_dict = {0:"Akshita",1:"Alisha",2:"Anjali",3:"Anurag",4:"Apoorva",5:"Harshit Chaudhary",6:"Ishaan",7:"Muskan",8:"Parth",9:"Pradhi", 10:"Radhika",11:"Riddika",12:"Sargam",13:"Satvik",14:"Sayantan",15:"Sidhi", 16:"Simran",17:"Trishant",18:"Tushar",19:"Vibhuti",20:"Vikas",21:"Vishant"}
    l = []
    folder_list = []
    key_list = list(label_dict.keys())
    val_list = list(label_dict.values())
    for folder_dir in dirs:
        d = 10000
        for img_path in folder_dir.glob("*"):  
            
            img1 = image.load_img(photo)
            # img1 = frontFace_db(photo)  
            img1 = image.img_to_array(img1) 
            img2 = image.load_img(img_path)
            #img2 = frontFace_db(img_path) 
            img2 = image.img_to_array(img2)
            image1,image2 = preprocess(img1,img2) 
            x1,x2 = model.predict((image1,image2))  
            dist = spatial.distance.cosine(x1, x2) 
            #print(dist)
            if dist<d:
                d = dist
                if dist == 0:
                    label = str(folder_dir).split("\\")[-1]
                    pos = val_list.index(label)
                    a = key_list[pos]
                    Id = str(a)
                    return Id 
             
        l.append(d)   
        minpos = l.index(min(l)) 
        #print(l)
        Id = str(minpos)
        # if l[minpos] <= 0.05:
        #     #name_result = label_dict[minpos]
        #     Id = str(minpos)
        # else:
        #     #name_result = "NA"
        #     Id = str(-1)
        #name_result = label_dict[minpos] 
        
    return Id


# In[25]:





# In[26]:





# In[ ]:





# In[ ]:





# In[ ]:




