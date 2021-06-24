import numpy as np
import tensorflow as tf
import cv2

img = cv2.imread(r"D:\jupyternotebooks\vgg16\signtrain\Hurt\Hurt.0ea60ac6-b265-11eb-bbf7-00f48dddcbd4.jpg")
# cv2.imshow("image", img)
img = cv2.resize(img, (64,64))
img = np.array(img, dtype="float32")
img = np.reshape(img, (1,64,64,3))


# Load the TFLite model and allocate tensors.
interpreter = tf.lite.Interpreter(model_path="model.tflite")
interpreter.allocate_tensors()

# Get input and output tensors.
input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()

# Test the model on random input data.
input_shape = input_details[0]['shape']

print("*"*50, input_details)
interpreter.set_tensor(input_details[0]['index'], img)

interpreter.invoke()

# The function `get_tensor()` returns a copy of the tensor data.
# Use `tensor()` in order to get a pointer to the tensor.
output_data = interpreter.get_tensor(output_details[0]['index'])
pred=np.argmax(output_data)
print(output_data)
print("**********************************")
print(pred)
