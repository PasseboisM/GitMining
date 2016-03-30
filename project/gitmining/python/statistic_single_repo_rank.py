from pandas import DataFrame
from scipy.stats import norm
import json
import sys

# Params:
# 1 A list of names of analyzed data
# 2 The object to be analyzed in JSON format

# Output:
# A list of ranks(0-1) for the data in corresponding order as argv[1]


df = DataFrame.from_csv("LocalData/repo_avg_std.csv")

headers = json.loads(sys.argv[1])
obj = json.loads(sys.argv[2])

for head in headers:
    avg = df['avg'][head]
    std = df['std'][head]
    norm_function = norm(loc=avg, scale=std)
    print norm_function.cdf(obj[head])

