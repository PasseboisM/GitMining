from pandas import DataFrame
from scipy.stats import norm
import numpy
import json
import sys

folder_name = 'D:/program/git/GitMiningByTeamMole/project/gitmining/LocalData/user/'
# user = '0-wiz-0' + '.txt'
user = sys.argv[1] + '.txt'
colomns = ['followers','following','public_gists','public_repos']

user_data_json = json.loads(open(folder_name+user,'r').read())
user_data = []
for colomn in colomns:
    user_data.append(user_data_json[colomn])

# print user_data
user_dataframe = DataFrame.from_csv("LocalData/user.csv")

statis_user_data = user_dataframe[colomns]
avg = numpy.average(statis_user_data,axis=0)
std = numpy.std(statis_user_data,axis=0)

norm_list = []
for i in range(len(colomns)):
    # print avg[i],std[i]
    norm_function = norm(loc=avg[i], scale=std[i])
    print norm_function.cdf(user_data[i])
