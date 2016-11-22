using System.Collections.Generic;
using rsc2016.ViewModels;

namespace rsc2016.Helpers.Models
{
    public class UserTokenResult
    {
        public UserTokenResult(string message)
        {
            Authenticated = false;
            Message = message;
        }

        public UserTokenResult(ErrorList errorList)
        {
            Authenticated = false;
            if (errorList.Errors.Count == 0)
                Message = errorList.Message;
            else Errors = errorList.Errors;
        }

        public UserTokenResult(bool authenticated, string token, double tokenExpires)
        {
            Authenticated = authenticated;
            Token = token;
            TokenExpires = tokenExpires;
        }

        public bool Authenticated { get; set; }
        public string Token { get; set; }
        public double TokenExpires { get; set; }
        public string Message { get; set; }
        public List<Error> Errors { get; set; }
    }
}