from pandas import DataFrame
from scipy.stats import norm
import json
import sys

# Params:
# 1 A list of names of analyzed data
# 2 The object to be analyzed in JSON format

# Output:
# A list of ranks(0-1) for the data in corresponding order as argv[1]


df = DataFrame.from_csv("LocalData/user_avg_std.csv")

input = open(sys.argv[1],'r')

headers = json.loads(input.readline())
obj = json.loads(input.readline())

for head in headers:
    avg = df['avg'][head]
    std = df['std'][head]
    norm_func = norm(loc=avg, scale=std)
    print norm_func.cdf(obj[head])
