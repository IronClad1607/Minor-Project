#!/usr/bin/env python
# coding: utf-8

# In[1]:


from keras.models import Model, load_model
import numpy as np
from keras.preprocessing import image
from scipy import spatial


# In[12]:


model = keras.models.load_model('inference_model.h5')


# In[28]:


def preprocess(img1,img2):
    img1 = cv2.resize(img1,(224,224))
    img1.astype(np.float32)/255.

    img2 = cv2.resize(img2,(224,224))
    img2.astype(np.float32)/255.
  
    img1 = img1.reshape(-1,224,224,3)
    img2 = img2.reshape(-1,224,224,3)
    return img1,img2


# In[30]:


def predict(photo1, photo2):
    img1 = image.load_img(photo1)
    img2 = image.load_img(photo2)
    img1 = image.img_to_array(img1)
    img2 = image.img_to_array(img2)
    img1,img2 = preprocess(img1,img2)
    x1,x2 = model.predict((img1,img2))
    dist = spatial.distance.cosine(x1, x2)
    #print(dist)
    if(dist<0.5):
        print("SAME")
    else:
        print("DIFFERENT")


# In[25]:





# In[26]:





# In[ ]:





# In[ ]:





# In[ ]:




