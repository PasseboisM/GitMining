from pandas import DataFrame
from scipy.stats import norm
import numpy
import json
import sys

# folder_name = 'LocalData/repository/'
# repo = '1stvamp/memorised' + '.txt'
# repo = sys.argv[1] + '.txt'
colomns = ['forks','open_issues','size','subscribers_count','watchers']

# repo_data_json = json.loads(open(folder_name+repo,'r').read())
# repo_data = []
# for colomn in colomns:
#     repo_data.append(repo_data_json[colomn])

# print repo_data
repo_dataframe = DataFrame.from_csv("LocalData/repo.csv")

statis_repo_data = repo_dataframe[colomns]
avg = numpy.average(statis_repo_data,axis=0)
std = numpy.std(statis_repo_data,axis=0)

avg_std_tuple = DataFrame(zip(avg,std),columns=['avg','std'])
avg_std_tuple.to_csv("LocalData/repo_avg_std.csv")

