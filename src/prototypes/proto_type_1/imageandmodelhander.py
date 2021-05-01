import numpy as np 
import tensorflow as tf
import keras
from tensorflow.keras.preprocessing.image import ImageDataGenerator
import cv2
import os 

def predict(img_dir):
        # a dict to hold all those labels and there string value 
        labels={
                    "0":"A",
                    "1":"B",
                    "2":"C",
                    "3":"D",
                    "4":"E",
                    "5":"F",
                    "6":"G",
                    "7":"H",
                    "8":"I",
                    "9":"J",
                    "10":"K",
                    "11":"L",
                    "12":"M",
                    "13":"N",
                    "14":"O",
                    "15":"P",
                    "16":"Q",
                    "17":"R",
                    "18":"S",
                    "19":"T",
                    "20":"U",
                    "21":"V",
                    "22":"W",
                    "23":"X",
                    "24":"Y",
                    "25":"Z",
                    "26":"DEL",
                    "27":"NOTHING",
                    "28":"SPACE"
        }


        # the file path should be the place where the model files are located 
        model = keras.models.load_model('D:\jupyternotebooks\prototype 1')

        input_img=ImageDataGenerator(rescale=1./255).flow_from_directory(img_dir,target_size=(64,64),class_mode=None)
        predications=model.predict(input_img)
        predications = np.argmax(predications, axis=1)
        predicted_label=labels.get(str(predications[0]))
        

        return predicted_label

def image_prepocess(name):
    
    img=cv2.imread(name)
     
    # path handling 
    path=r"C:\Users\prajw\OneDrive\Desktop\Github\Sign_Language_Interpreter\src\proto_type_1\img_buffer\img_buffer"
    path2=r"C:\Users\prajw\OneDrive\Desktop\Github\Sign_Language_Interpreter\src\proto_type_1\img_buffer"
    path3=r"C:\Users\prajw\OneDrive\Desktop\Github\Sign_Language_Interpreter\src\proto_type_1\classi_img_buffer"
    cv2.imwrite(os.path.join(path,"classifed.png"),img)

    predicted_string=str(predict(path2))

    font=cv2.FONT_HERSHEY_COMPLEX
    fontScale=1
    color = (0, 255, 0)
    org = (50, 50)
    thickness = 2
    image=cv2.putText(
        img,
        predicted_string,
        org,
        font,
        fontScale,
        color,
        thickness,
        cv2.LINE_AA
    )

    cv2.imwrite(os.path.join(path3,"detected.png "),image)