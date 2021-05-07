import os 
import xml.etree.ElementTree as ET

directory=r"D:\Dataset\L"

for filename in os.listdir(directory):
    if filename.endswith('.xml'):
            path=os.path.join(directory,filename)
            tree=ET.parse(path)
            xmlroot=tree.getroot()
            xmlobject=xmlroot.find("object")
            xmlobject.find("name").text="L"
            tree.write(path)
            