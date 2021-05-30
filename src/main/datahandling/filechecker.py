import os 

count =0
directory=r"D:\jupyternotebooks\mainsrc\Tensorflow\workspace\images\test"

for filename in os.listdir(directory):
    if filename.endswith('.xml'):
        img_filename=filename[:-4]+".jpg"
        # print(img_filename)
        img_path=os.path.join(directory,img_filename)
        if not (os.path.exists(img_path)):
            count+=1
            path=os.path.join(directory,filename)
            os.remove(path)
print(count)
            
