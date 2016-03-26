from pandas import DataFrame
from scipy.stats import norm
import numpy
import json
import sys

folder_name = 'LocalData/repository/'
# repo = '1stvamp/memorised' + '.txt'
repo = sys.argv[1] + '.txt'
colomns = ['forks','open_issues','size','subscribers_count','watchers']

repo_data_json = json.loads(open(folder_name+repo,'r').read())
repo_data = []
for colomn in colomns:
    repo_data.append(repo_data_json[colomn])

# print repo_data
repo_dataframe = DataFrame.from_csv("LocalData/repo.csv")

statis_repo_data = repo_dataframe[colomns]
avg = numpy.average(statis_repo_data,axis=0)
std = numpy.std(statis_repo_data,axis=0)

norm_list = []
for i in range(len(colomns)):
    # print avg[i],std[i]
    norm_function = norm(loc=avg[i], scale=std[i])
    print norm_function.cdf(repo_data[i])
