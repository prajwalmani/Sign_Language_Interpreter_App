labels=['A','Airplane','B','Bathroom','Bye',
        'C','D','E','F','G','H','Hello','Help','Hurt','I','I Love You',
        'J','K','L','M','More','N','No','O','P','Play','Q','R','S',
        'Something','Sorry','T','Thank you','U','V','W','X','Y','Yes','Z']
with open('labels.txt','w') as f:
    f.write('\n'.join(labels))