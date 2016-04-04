from pandas import DataFrame
import json
import sys



input = open(sys.argv[1],'r')
languages = json.loads(input.readline())

user_dataframe = DataFrame.from_csv("LocalData/repo.csv")
lang_list = user_dataframe['language']

for i in range(len(languages)):
    count = 0
    for lan in lang_list:
        if languages[i]==lan:
            count += 1
    print count