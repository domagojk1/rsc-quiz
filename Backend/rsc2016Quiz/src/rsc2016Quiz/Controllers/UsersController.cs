using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using rsc2016Quiz.Dtos;
using rsc2016Quiz.Services;

namespace rsc2016Quiz.Controllers
{
    [Route("api/[controller]")]
    public class UsersController : Controller
    {
        private readonly IOAuthHandler _authHandler;

        public UsersController(IOAuthHandler authHandler)
        {
            _authHandler = authHandler;
        }

        [HttpPost("login")]
        //    [Produces(typeof(TokenDto))]
        public async Task<IActionResult> Login([FromBody] FbToken fbtoken)
        {

            var result = await _authHandler.VerifyFacebookAccessToken(fbtoken.Token);
            if (result)
            {
                return Ok("true");
            }
            return Ok("false");
        }


        //   private readonly IMembershipService _membershipService;
        //  private readonly IApiErrorHandler _apiErrorHandler;

        /*
        public UsersController(IMembershipService membershipService, IApiErrorHandler apiErrorHandler)
        {
            _membershipService = membershipService;
            _apiErrorHandler = apiErrorHandler;
        }
        */

        /*
        [HttpPost("login")]
    //    [Produces(typeof(TokenDto))]
        public async Task<IActionResult> Login([FromBody]LoginDto loginDto)
        {
            var result = await _membershipService.LoginToken(loginDto);
            if (result.Authenticated)
            {
                return Ok(Mapper.Map<TokenDto>(result));
            }
            return BadRequest(_apiErrorHandler.GenerateErrorDto(result)); //TODO error kreator
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register([FromBody] RegisterViewModel registerViewModel)
        {
            var result = await _membershipService.Register(registerViewModel);
            if (result.Status)
            {
                return Ok();
            }
            return BadRequest(_apiErrorHandler.GenerateErrorDto(result));

        }

        [Authorize("Bearer")]
        [HttpGet("refreshToken")]
        [Produces(typeof(TokenDto))]
        public IActionResult RefreshToken()
        {

            return Ok(_membershipService.RefreshTokens(User.Identity.Name));

        }
        */
    }
    }
