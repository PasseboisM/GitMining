import json
import os
from pandas import DataFrame



def byteify(input):
    if isinstance(input, dict):
        return {byteify(key):byteify(value) for key,value in input.iteritems()}
    elif isinstance(input, list):
        return [byteify(element) for element in input]
    elif isinstance(input, unicode):
        return input.encode('utf-8')
    else:
        return input


folder_name = 'D:/program/git/GitMiningByTeamMole/project/gitmining/LocalData/repository/'
json_datas = []
for subfolder in os.listdir(folder_name):
    for file in os.listdir(folder_name+subfolder):
        repo_data = json.loads(open(folder_name+subfolder+'/'+file,'r').read())
        json_datas.append(repo_data)
json_datas = byteify(json_datas)
user_datas = DataFrame(json_datas)
user_datas.to_csv("LocalData/repo.csv")
# print 'done'

