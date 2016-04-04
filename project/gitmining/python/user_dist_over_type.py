from pandas import DataFrame
import json
import sys


df = DataFrame.from_csv("LocalData/user_dist_over_type.csv")

input = open(sys.argv[1],'r')

headers = json.loads(input.readline())

for head in headers:
    count = df['count'][head]
    print count