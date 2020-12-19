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


def predict(photo):
    #p = Path("bollywood_celeb_faces_0/")
    p = Path("dataset111/")
    dirs = p.glob("*")
    
    label_dict = {0:"Aamir",1:"Salman",2:"Shahrukh"}
    l = []
    for folder_dir in dirs:
        #label = str(folder_dir).split("\\")[-1]
        d = 10000
        for img_path in folder_dir.glob("*.jpg"):
            
            img1 = image.load_img(photo)
            img1 = image.img_to_array(img1)
            img2 = image.load_img(img_path)
            img2 = image.img_to_array(img2)
            image1,image2 = preprocess(img1,img2)
            x1,x2 = model.predict((image1,image2))
            dist = spatial.distance.cosine(x1, x2)
            #print(dist)
            if dist<d:
                d = dist
            
        l.append(d)
        minpos = l.index(min(l))
        name_result = label_dict[minpos]
    
    return name_result


# In[25]:





# In[26]:





# In[ ]:





# In[ ]:





# In[ ]:




