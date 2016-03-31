from pandas import DataFrame
from scipy.stats import norm
import numpy
import json
import sys

colomns = ['followers','following','public_gists','public_repos']

user_dataframe = DataFrame.from_csv("LocalData/user.csv")

statis_user_data = user_dataframe[colomns]
avg = numpy.average(statis_user_data,axis=0)
std = numpy.std(statis_user_data,axis=0)
# print zip(avg,std)
avg_std_tuple = DataFrame(zip(colomns,avg,std),columns=['head','avg','std'])
avg_std_tuple.to_csv("LocalData/user_avg_std.csv")


