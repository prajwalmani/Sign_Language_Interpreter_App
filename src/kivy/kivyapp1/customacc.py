import os 
import numpy as np 
import tensorflow as tf
import keras
from tensorflow.keras.preprocessing.image import ImageDataGenerator

path='datasets\prototype1\custom_sign_images\custom_images'
dir=os.listdir(path)
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
        
def file_handling():

    for index, file in enumerate(dir):
        file_location=os.path.join(path, file)
        if "nothing" not in file_location and "space" not in file_location:
            alpha=file_location[-5]
            os.rename(os.path.join(path, file), os.path.join(path, ''.join([alpha, '.jpg'])))
            
        elif "nothing" in file_location:
            os.rename(os.path.join(path, file), os.path.join(path, ''.join(['NOTHING', '.jpg'])))
            
        else:
            os.rename(os.path.join(path, file), os.path.join(path, ''.join(['SPACE', '.jpg'])))

def predict(img_dir):

        # the file path should be the place where the model files are located 
        model = keras.models.load_model('D:\jupyternotebooks\prototype 1')

        input_img=ImageDataGenerator(rescale=1./255).flow_from_directory(img_dir,target_size=(64,64),class_mode=None)
        predications=model.predict(input_img)
        predications = np.argmax(predications, axis=1)
        return predications
    
path2='datasets\prototype1\custom_sign_images'
predictions=predict(path2)
predict_list=[]
for item in predictions:
    predicted_label=labels.get(str(item))
    predict_list.append(predicted_label)

filenames=[]

for index, file in enumerate(dir):
    filenames.append(file[:-4])
count =0
for f,p in zip(filenames,predict_list):
    if f == p:
        count=+1
accuracy_precentage=(count/len(filenames))*100

print("The total number of classes is {}".format(len(filenames)))
print("The final accuracy of VGG16 is {}".format(accuracy_precentage))
