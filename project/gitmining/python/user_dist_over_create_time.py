from pandas import DataFrame
import json
import sys
import util


input = open(sys.argv[1],'r')
divisions = json.loads(input.readline())

user_dataframe = DataFrame.from_csv("LocalData/user.csv")
time_list = user_dataframe['created_at']

for i in range(len(divisions)-1):
    count = util.count_ele(time_list,divisions[i],divisions[i+1])
    print count