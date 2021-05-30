import cv2
import os 
import xml.etree.ElementTree as ET


source_dir=input("Enter the source dir path:")
destination_dir=input("Enter the destination dir path:")

for filename in os.listdir(source_dir):
    if filename.endswith('.xml'):
        ymin=0
        ymax=0
        xmin=0
        xmax=0
        img_filename=filename[:-4]+".jpg"
        xml_path=os.path.join(source_dir,filename)
        tree=ET.parse(xml_path)
        xmlroot=tree.getroot()
        xmlobject=xmlroot.find("object")
        xmlbndbox=xmlobject.find("bndbox")
        ymin=int(xmlbndbox.find("ymin").text)
        ymax=int(xmlbndbox.find("ymax").text)
        xmin=int(xmlbndbox.find("xmin").text)
        xmax=int(xmlbndbox.find("xmax").text)
        img_path=os.path.join(source_dir,img_filename)
        img=cv2.imread(img_path)
        try:
            crop_img=img[ymin:ymax,xmin:xmax]
        except TypeError:
            continue
        dest_img_path=os.path.join(destination_dir,img_filename)
        try:
            cv2.imwrite(dest_img_path,crop_img)
        except:
            continue

# cv2.imshow("cropped", crop_img)
# cv2.waitKey(0)