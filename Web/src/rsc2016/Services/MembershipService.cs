using System;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Security.Principal;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Microsoft.IdentityModel.Tokens;
using rsc2016.Helpers;
using rsc2016.Helpers.Models;
using rsc2016.Models;
using rsc2016.Services.Interfaces;
using rsc2016.ViewModels;
using SL.Core.Authentication;

namespace rsc2016.Services
{
    public class MembershipService : IMembershipService
    {
        private SignInManager<User> _signInManager;
        private readonly UserManager<User> _userManager;
        private readonly TokenAuthOptions _tokenOptions;

        public MembershipService(SignInManager<User> signInManager, UserManager<User> userManager, TokenAuthOptions tokenOptions)
        {
            _signInManager = signInManager;
            _userManager = userManager;
            _tokenOptions = tokenOptions;
        }

        public async Task<Result> Login(LoginViewModel model)
        {
            var modelState = ModelStateValidator<LoginViewModel>.GetModelErrors(model);
            if (modelState.IsValid())
            {
                var result = await _signInManager.PasswordSignInAsync(model.Username, model.Password, model.RememberMe, false);

                if (result.Succeeded)
                {
                    return ResultManager.Success();
                }    
                    // ModelState.AddModelError("", error.Description);
                    return ResultManager.Failed("Invalid login info");
            }
            return ResultManager.ModelFailed(modelState);
            //   ModelState.AddModelError("", "Invalid login attempt");
        }

        public async Task<UserTokenResult> LoginToken(LoginViewModel model)
        {
            var modelState = ModelStateValidator<LoginViewModel>.GetModelErrors(model);
            if (modelState.IsValid())
            {
                var result = await _signInManager.PasswordSignInAsync(model.Username, model.Password, model.RememberMe, false);

                if (result.Succeeded)
                {
                    DateTime expires = DateTime.UtcNow.AddMinutes(4320);  //TODO Configuration expires
                    return new UserTokenResult(true, GetToken(model.Username, expires), Helper.DatetimeToMiliseconds(expires));
                }
                // ModelState.AddModelError("", error.Description);
                return new UserTokenResult("Invalid data");
            }
            return new UserTokenResult(modelState);

        }

        public async Task<Result> Register(RegisterViewModel model)
        {
            var modelState = ModelStateValidator<RegisterViewModel>.GetModelErrors(model);
            if (modelState.IsValid())
            {
                var user = new User { UserName = model.Username };
                var result = await _userManager.CreateAsync(user, model.Password);

                if (result.Succeeded)
                {
                 //   await _signInManager.SignInAsync(user, false);
                    return ResultManager.Success();
                }
                else
                {
                    var error = result.Errors.First();
                        // ModelState.AddModelError("", error.Description);
                        return ResultManager.Failed(error.Description);
                    
                }
            }
            return ResultManager.ModelFailed(modelState);
        }

        #region Tokens

        public UserTokenResult RefreshTokens(string userName)
        {
            DateTime expires = DateTime.UtcNow.AddMinutes(4320); //TODO Config expires
            return new UserTokenResult(true, GetToken(userName, expires), Helper.DatetimeToMiliseconds(expires));
        }


        public string GetToken(string userName, DateTime? expires)
        {
            var handler = new JwtSecurityTokenHandler();

            // Here, you should create or look up an identity for the userName which is being authenticated.
            // For now, just creating a simple generic identity.
            ClaimsIdentity identity = new ClaimsIdentity(new GenericIdentity(userName, "TokenAuth"), new[] { new Claim("UserId", userName, ClaimValueTypes.String) });
            var descriptor = new SecurityTokenDescriptor
            {
                Issuer = _tokenOptions.Issuer,
                Audience = _tokenOptions.Audience,
                SigningCredentials = _tokenOptions.SigningCredentials,
                Subject = identity,
                Expires = expires

            };

            var securityToken = handler.CreateToken(descriptor);
            return handler.WriteToken(securityToken);
        }

        #endregion
    }
}
