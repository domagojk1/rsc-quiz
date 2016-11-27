using System.Threading.Tasks;
using rsc2016Quiz.Dtos;
using rsc2016Quiz.Dtos.Authentication;
using rsc2016Quiz.Helpers.ResultModels;

namespace rsc2016Quiz.Services.Interfaces
{
    public interface IMembershipService
    {
        Task<UserTokenResult> LoginToken(LoginDto model);
        bool Register(LoginDto model);
        UserTokenResult RefreshTokens(string userName);
    }
}
