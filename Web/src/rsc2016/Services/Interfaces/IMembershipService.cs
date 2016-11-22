using System.Threading.Tasks;
using rsc2016.Helpers.Models;
using rsc2016.ViewModels;

namespace rsc2016.Services.Interfaces
{
    public interface IMembershipService
    {
        Task<Result> Login(LoginViewModel loginViewModel);
        Task<UserTokenResult> LoginToken(LoginViewModel loginViewModel);
        Task<Result> Register(RegisterViewModel model);
        UserTokenResult RefreshTokens(string userName);
    }
}