import os 
import stat

directory=r"C:\Users\prajw\OneDrive\Desktop\Giphyjs\Dataset"
train_dir=r"C:\Users\prajw\OneDrive\Desktop\Giphyjs\data\train"
test_dir=r"C:\Users\prajw\OneDrive\Desktop\Giphyjs\data\test"

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
