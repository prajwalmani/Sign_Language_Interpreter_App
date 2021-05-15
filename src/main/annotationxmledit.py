import os 
import xml.etree.ElementTree as ET

flag=0
directory=r"D:\jupyternotebooks\mainsrc\Tensorflow\workspace\images"
train_annot_dir=r"C:\Users\prajw\OneDrive\Desktop\Github\Sign_Language_Interpreter\src\main\annotations\train_annot"
test_annot_dir=r"C:\Users\prajw\OneDrive\Desktop\Github\Sign_Language_Interpreter\src\main\annotations\test_annot"

for root,dirs,files in os.walk(directory):
    for filename in os.listdir(root):
        if filename.endswith('.xml'):
            if flag==1:
                foldername ="train"
                path=os.path.join(root,filename)
                tree=ET.parse(path)
                xmlroot=tree.getroot()
                xmlfilename=xmlroot.find("filename").text
                xmlpath=xmlroot.find("path")
                xmlpath=os.path.join(root,xmlfilename)
                xmlroot.find("path").text=xmlpath
                xmlroot.find("folder").text=foldername
                tree.write(path)
                os.system('copy {0} {1}'.format(path,train_annot_dir))
            if flag==2:
                foldername ="test"
                path=os.path.join(root,filename)
                tree=ET.parse(path)
                xmlroot=tree.getroot()
                xmlfilename=xmlroot.find("filename").text
                xmlpath=xmlroot.find("path")
                xmlpath=os.path.join(root,xmlfilename)
                xmlroot.find("path").text=xmlpath
                xmlroot.find("folder").text=foldername
                tree.write(path)
                os.system('copy {0} {1}'.format(path,test_annot_dir))
    flag+=1       
    
                
            
