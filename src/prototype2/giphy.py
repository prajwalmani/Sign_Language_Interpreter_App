import giphy_client as gc
from giphy_client.rest import ApiException
import requests

api_instance = gc.DefaultApi()
api_key="your api key"
term=input("Enter the search query term:")
query='@signwithrobert+{}'.format(term)
fmt = 'gif'
limit=1


def giphy_search():
    try :
        response=api_instance.gifs_search_get(api_key,query,limit=1,fmt=fmt)
        gif_id = response.data[0]
        url_gif = gif_id.images.downsized.url
    except ApiException:
        print("Exception when calling DefaultApi->gifs_search_get: %s\n" % e)

    with open('test.gif','wb') as f:
        f.write(requests.get(url_gif).content)

    print("Done!")
giphy_search()



    
