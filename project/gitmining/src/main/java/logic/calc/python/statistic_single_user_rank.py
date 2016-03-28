from pandas import DataFrame
from scipy.stats import norm
df = DataFrame.from_csv("LocalData/user_avg_std.csv")
# print df
avg = df['avg']
std = df['std']
norm_list = []
for i in range(len(colomns)):
    # print avg[i],std[i]
    norm_function = norm(loc=avg[i], scale=std[i])
    print norm_function.cdf(user_data[i])