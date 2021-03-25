import numpy as np 
import tensorflow as tf
import keras
from tensorflow.keras.preprocessing.image import ImageDataGenerator


def predict():
        # a dict to hold all those labels and there string value 
        labels={
                    "0":"A",
                    "1":"B",
                    "2":"C",
                    "3":"D",
                    "4":"DEL",
                    "5":"E",
                    "6":"F",
                    "7":"G",
                    "8":"H",
                    "9":"I",
                    "10":"J",
                    "11":"K",
                    "12":"L",
                    "13":"M",
                    "14":"N",
                    "15":"NOTHING",
                    "16":"O",
                    "17":"P",
                    "18":"Q",
                    "19":"R",
                    "20":"S",
                    "21":"SPACE",
                    "22":"T",
                    "23":"U",
                    "24":"V",
                    "25":"W",
                    "26":"X",
                    "27":"Y",
                    "28":"Z"
        }

        # the file path should be the place where the model files are located 
        model = keras.models.load_model('D:\jupyternotebooks\prototype 1')

        # the file path should be the location of the user input
        img_dir=r"D:\jupyternotebooks\test"


        input_img=ImageDataGenerator(rescale=1./255).flow_from_directory(img_dir,target_size=(64,64),class_mode=None)
        predications=model.predict(input_img)
        predications = np.argmax(predications, axis=1)
        print(labels.get(str(predications[0])))