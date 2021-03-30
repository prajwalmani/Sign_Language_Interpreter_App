import cv2
import os 
import modelhander as md

def image_prepocess(name):
    
    img=cv2.imread(name)
     
    # path handling 
    path=r"C:\Users\prajw\OneDrive\Desktop\Github\Sign_Language_Interpreter\src\proto_type_1\img_buffer\img_buffer"
    path2=r"C:\Users\prajw\OneDrive\Desktop\Github\Sign_Language_Interpreter\src\proto_type_1\img_buffer"
    path3=r"C:\Users\prajw\OneDrive\Desktop\Github\Sign_Language_Interpreter\src\proto_type_1\classi_img_buffer"
    cv2.imwrite(os.path.join(path,"classifed.png"),img)

    predicted_string=str(md.predict(path2))

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

    cv2.imwrite(os.path.join(path3,"detected.png"),image)




















    # resized_img=cv2.resize(imag,(int(imag.shape[1]/3),int(imag.shape[0]/3)))
    # cv2.imwrite("classified.png",resized_img)

