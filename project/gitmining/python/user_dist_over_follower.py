from pandas import DataFrame
import json
import sys
import util

# Param:
# A list of integers(JSON) as gap for the ranges.

# Result:
# Lines of integers telling number of users in each range.




input = open(sys.argv[1],'r')
gaps = json.loads(input.readline())


user_dataframe = DataFrame.from_csv("LocalData/user.csv")
follower_list = user_dataframe['followers']

start = 0
for i in range(len(gaps)):
    count = util.count_ele(follower_list,start,start+gaps[i])
    start += gaps[i]
    print count



