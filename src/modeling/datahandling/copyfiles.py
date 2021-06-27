import os 
import stat
import time 

directory=r"capturedatasets"
train_dir=r"train"
test_dir=r"test"

for root,dirs,files in os.walk(directory):
    count=1
    for filename in os.listdir(root):
        path=os.path.join(root,filename)
        if count ==1:
            count+=1
            continue
        if count<=1600:
            train_path=os.path.join(train_dir,filename)
            os.system('copy {0} {1}'.format(path,train_path))
        else:
            test_path=os.path.join(test_dir,filename)
            os.system('copy {0} {1}'.format(path,test_path))
        print("We are in {}".format(count))
        count+=1
    time.sleep(5)
