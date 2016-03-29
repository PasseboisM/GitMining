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

headers = json.loads(sys.argv[1])
obj = json.loads(sys.argv[2])
# headers = json.loads('["followers", "following", "public_gists", "public_repos"]')
# obj = json.loads('{"login":"AaronH","id":4810,"avatar_url":"https://avatars.githubusercontent.com/u/4810?v\u003d3",' \
#       '"gravatar_id":"","url":"https://api.github.com/users/AaronH","html_url":"https://github.com/Aaron' \
#       'H","followers_url":"https://api.github.com/users/AaronH/followers","following_url":"https://api.gi' \
#       'thub.com/users/AaronH/following{/other_user}","gists_url":"https://api.github.com/users/AaronH/gis' \
#       'ts{/gist_id}","starred_url":"https://api.github.com/users/AaronH/starred{/owner}{/repo}","subscripti' \
#       'ons_url":"https://api.github.com/users/AaronH/subscriptions","organizations_url":"https://api.github' \
#       '.com/users/AaronH/orgs","repos_url":"https://api.github.com/users/AaronH/repos","events_url":"https:' \
#       '//api.github.com/users/AaronH/events{/privacy}","received_events_url":"https://api.github.com/users/' \
#       'AaronH/received_events","type":"User","site_admin":false,"name":"Aaron Hurley","blog":"http://zealog' \
#       '.com","location":"San Jose, CA","email":"aaron@showtimefu.com","public_repos":24,"public_gists":2,"fol' \
#       'lowers":5,"following":0,"created_at":"2008-04-03T23:45:34Z","updated_at":"2015-10-02T17:42:52Z"}')


# user_data = []
# for i in xrange(1,1+len(df)):
#     user_data.append(float(sys.argv[i]))
# avg = df['avg']
# std = df['std']
# norm_list = []
# for i in range(len(df)):
#     norm_function = norm(loc=avg[i], scale=std[i])
#     print norm_function.cdf(user_data[i])