import os 

directory=r"D:\Capture Dataset\capture datasets"
sign_train_dir=r"D:\jupyternotebooks\vgg16\signtrain"
count=0
flag=0

def createdir(list_dir):
    for dir_name in list_dir:
        path=os.path.join(sign_train_dir,dir_name)
        try:
            os.mkdir(path)
        except FileExistsError:
            print("Dirs already excisted!")
            return
    print("Dirs created!")
    
    
for root,dirs,files in os.walk(directory):
    count+=1
    if flag==0:
        list_dir=dirs
        createdir(list_dir)
        flag+=1
    try:
        sign_train_paths=os.path.join(sign_train_dir,list_dir[int(count)-1])
        # print(sign_train_paths)
    except IndexError:
        break
    for filename in os.listdir(root):
        print(filename)
        if filename.endswith('.xml'):
            pass
        else:
            file_path=os.path.join(root,filename)
            sign_train_path=os.path.join(sign_train_paths,filename)
            print(sign_train_path)
            # os.system('copy {0} {1}'.format(file_path,sign_train_path))
print("Files Copied!")