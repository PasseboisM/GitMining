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

folder_name = os.path.abspath('.')+'/LocalData/user/'
json_datas = []
for file in os.listdir(folder_name):
    user_data = json.loads(open(folder_name+file,'r').read())
    json_datas.append(user_data)
json_datas = byteify(json_datas)
user_datas = DataFrame(json_datas)
user_datas.to_csv("LocalData/user.csv")

