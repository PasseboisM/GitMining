from pandas import DataFrame
from scipy.stats import norm
import numpy
import json
import sys

colomns = ['forks','open_issues','size','subscribers_count','watchers']

repo_dataframe = DataFrame.from_csv("LocalData/repo.csv")

statis_repo_data = repo_dataframe[colomns]
avg = numpy.average(statis_repo_data,axis=0)
std = numpy.std(statis_repo_data,axis=0)

avg_std_tuple = DataFrame(zip(colomns,avg,std),columns=['head','avg','std'])
avg_std_tuple.to_csv("LocalData/repo_avg_std.csv")

