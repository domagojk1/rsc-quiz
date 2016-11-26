using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using rsc2016Quiz.Dtos.Authentication;
using rsc2016Quiz.Helpers.ResultModels;

namespace rsc2016Quiz.Services
{
    public interface IOAuthHandler
    {
        Task<LoginDto> VerifyFacebookAccessToken(string accessToken);
    }
}
