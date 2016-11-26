using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace rsc2016Quiz.Services
{
    public interface OAuthHandler
    {
        Task<bool> VerifyFacebookAccessToken(string accessToken);
    }
}
