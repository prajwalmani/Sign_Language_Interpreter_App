import os 
import xml.etree.ElementTree as ET

directory=r"D:\jupyternotebooks\main src\dataset"

for root,dirs,files in os.walk(directory):
    for filename in os.listdir(root):
        if filename.endswith('.xml'):
            path=os.path.join(root,filename)
            tree=ET.parse(path)
            xmlroot=tree.getroot()
            xmlfilename=xmlroot.find("filename").text
            xmlpath=xmlroot.find("path")
            xmlpath=os.path.join(root,xmlfilename)
            xmlroot.find("path").text=xmlpath
            tree.write(path)
            
            
            