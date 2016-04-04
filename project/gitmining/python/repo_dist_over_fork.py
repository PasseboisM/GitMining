from pandas import DataFrame
import json
import sys
import util



input = open(sys.argv[1],'r')
gaps = json.loads(input.readline())

user_dataframe = DataFrame.from_csv("LocalData/repo.csv")
fork_list = user_dataframe['forks']

start = 0
for i in range(len(gaps)):
    count = util.count_ele(fork_list,start,start+gaps[i])
    start += gaps[i]
    print count