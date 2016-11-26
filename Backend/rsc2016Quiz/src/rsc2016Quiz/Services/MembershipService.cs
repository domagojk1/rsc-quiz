using System;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Principal;
using System.Threading.Tasks;
using Microsoft.IdentityModel.Tokens;
using rsc2016Quiz.Authentication;
using rsc2016Quiz.Dtos.Authentication;
using rsc2016Quiz.Helpers;
using rsc2016Quiz.Helpers.ResultModels;
using rsc2016Quiz.Models;
using rsc2016Quiz.Repository;
using rsc2016Quiz.Services.Interfaces;

namespace rsc2016Quiz.Services
{
    public class MembershipService : IMembershipService
    {
        private readonly TokenAuthOptions _tokenOptions;
        private readonly IUserRepository _userRepository;
        private readonly IEncryptionService _encryptionService;

        public MembershipService(TokenAuthOptions tokenOptions, IUserRepository userRepository, IEncryptionService encryptionService)
        {
            _tokenOptions = tokenOptions;
            _userRepository = userRepository;
            _encryptionService = encryptionService;
        }


        public async Task<UserTokenResult> LoginToken(LoginDto model)
        {
            var user = await _userRepository.GetUserByEmail(model.name);
            DateTime expires = DateTime.UtcNow.AddMinutes(4320);
            if (user != null)
            {
                
                GetToken(user.UserName, expires);
                return new UserTokenResult(true, GetToken(model.name, expires), Helper.DatetimeToMiliseconds(expires));
            }
            Register(model);
            GetToken(model.name, expires);
            return new UserTokenResult(true, GetToken(model.name, expires), Helper.DatetimeToMiliseconds(expires));
        }

        public bool Register(LoginDto model)
        {
            var passwordSalt = _encryptionService.CreateSalt();
            var user = CreateUser(model.id, "nebitno", passwordSalt, model.name);
            _userRepository.AddUser(user);
            return true;
        }

        private User CreateUser(string username, string password, string salt, string email)
        {
            var user = new User
            {
                UserName = username,
                Salt = salt,
                Email = email,
                PasswordHash = _encryptionService.EncryptPassword(password, salt),
                Created = DateTime.Now,
                
            };
            return user;
        }


        #region Tokens

        public UserTokenResult RefreshTokens(string userName)
        {
            DateTime expires = DateTime.UtcNow.AddMinutes(4320); //TODO Config expires
            return new UserTokenResult(true, GetToken(userName, expires), Helper.DatetimeToMiliseconds(expires));
        }


        public string GetToken(string email, DateTime? expires)
        {
            var handler = new JwtSecurityTokenHandler();

            // Here, you should create or look up an identity for the email which is being authenticated.
            // For now, just creating a simple generic identity.
            ClaimsIdentity identity = new ClaimsIdentity(new GenericIdentity(email, "TokenAuth"), new[] { new Claim("UserId", email, ClaimValueTypes.String) });
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
