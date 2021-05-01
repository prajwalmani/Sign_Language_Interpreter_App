import cv2 
import uuid
import os
import time

image_path=r'C:\Users\prajw\OneDrive\Desktop\algo\images'
no_of_images=50

while True:
    label=input("enter the label name or q to exit :")
    if label =='q':
       print("Existing!")
       break
    else:
        os.mkdir(r'C:\Users\prajw\OneDrive\Desktop\algo\images\{}'.format(label))
        c = cv2.VideoCapture(0)
        print("Collecting images foe the label {}".format(label))
        for n in range(no_of_images):
            print("Collecting images for {}".format(n))
            ret, frame = c.read()
            imgname=os.path.join(image_path,label,label+'.'+'{}.jpg'.format(str(uuid.uuid1())))
            cv2.imwrite(imgname, frame)
            cv2.imshow('frame', frame)
            time.sleep(2)
        c.release()
    
    
print("Success!")