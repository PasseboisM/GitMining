from pandas import DataFrame
from scipy.stats import norm
import json
import sys

df = DataFrame.from_csv("LocalData/repo_avg_std.csv")
# print len(df)

user_data = []
for i in xrange(1,1+len(df)):
    user_data.append(float(sys.argv[i]))
# print user_data
avg = df['avg']
std = df['std']
norm_list = []
for i in range(len(df)):
    # print avg[i],std[i]
    norm_function = norm(loc=avg[i], scale=std[i])
    print norm_function.cdf(user_data[i])