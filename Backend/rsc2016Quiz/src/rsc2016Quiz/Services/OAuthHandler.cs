using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace rsc2016Quiz.Services
{
    public class OAuthHandler : IOAuthHandler
    {
        public async Task<bool> VerifyFacebookAccessToken(string accessToken)
        {
            var path = "https://graph.facebook.com/me?access_token=" + accessToken;
            var client = new HttpClient();
            var uri = new Uri(path);
            var response = await client.GetAsync(uri);
            var content = await response.Content.ReadAsStringAsync();
            if (response.IsSuccessStatusCode)
            {
               
             //   fbUser = Newtonsoft.Json.JsonConvert.DeserializeObject<FacebookUserViewModel>(content);
                return true;
            }
            return false;
        }
    }
}
