using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using rsc2016.Dtos;
using rsc2016.Helpers;
using rsc2016.Services.Interfaces;
using rsc2016.ViewModels;

// For more information on enabling Web API for empty projects, visit http://go.microsoft.com/fwlink/?LinkID=397860

namespace rsc2016.Controllers.Api
{
    [Route("api/[controller]")]
    public class UsersController : Controller
    {
        private readonly IMembershipService _membershipService;
        private readonly IApiErrorHandler _apiErrorHandler;

        public UsersController(IMembershipService membershipService, IApiErrorHandler apiErrorHandler)
        {
            _membershipService = membershipService;
            _apiErrorHandler = apiErrorHandler;
        }

        [HttpPost("login")]
        [Produces(typeof(TokenDto))]
        public async Task<IActionResult> Login([FromBody]LoginViewModel loginViewModel)
        {
            var result = await _membershipService.LoginToken(loginViewModel);
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

    }
}
