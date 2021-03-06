﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR.Infrastructure;
using rsc2016Quiz.Dtos;
using rsc2016Quiz.Helpers;
using rsc2016Quiz.Helpers.ResultModels;
using rsc2016Quiz.Models;
using rsc2016Quiz.Repository;
using rsc2016Quiz.SignalR;

namespace rsc2016Quiz.Controllers
{
    [Route("api/[controller]")]
    public class TeamsController : Controller
    {
        private readonly ITeamRepository _teamRepository;
        private readonly IUserRepository _userRepository;
        private readonly IApiErrorHandler _apiErrorHandler;
        private readonly IEventRepository _eventRepository;
        private readonly IConnectionManager _connectionManager;

        public TeamsController(ITeamRepository teamRepository, IUserRepository userRepository, IApiErrorHandler apiErrorHandler, IEventRepository eventRepository, IConnectionManager connectionManager)
        {
            _teamRepository = teamRepository;
            _userRepository = userRepository;
            _apiErrorHandler = apiErrorHandler;
            _eventRepository = eventRepository;
            _connectionManager = connectionManager;
        }

        [Authorize]
        [HttpPost("join")]
        [Produces(typeof(MessageDto))]
        public IActionResult JoinTeam([FromBody] TeamJoinDto model)
        {
            var result = _teamRepository.GetTeamById(model.TeamId);
            if (result != null)
            {
                if (result.EventId == model.EventId && result.Password == model.Password) //TODO team max
                {
                    var userName = User.Identity.Name;
                    var user = _userRepository.GetUserByEmail(userName);
                    _teamRepository.AddMememberToTeam(model.TeamId,user.Result);
                    return Ok(new MessageDto("Succesfull"));
                }
            }
            return BadRequest(_apiErrorHandler.GenerateErrorDto(new ErrorList("Invalid data")));
        }

        [HttpPost("Create")]
        [Produces(typeof(TeamPwDto))]
        public IActionResult CreateTeam([FromBody] TeamCreateDto model)
        {
            var result = _eventRepository.GetEventById(model.EventId);
            if (result != null)
            {
                if (result.IsOpen)
                {
                    var password = Helper.GenRandomPassword().ToString();
                    var team = new Team()
                    {
                        Name = model.Name,
                        EventId = model.EventId,
                        Password = password
                    };
                    _teamRepository.Add(team);
                    return Ok(new TeamPwDto(password,team.Id,result.Id));
                }
                var ev = _eventRepository.GetEventById(model.EventId);
                _connectionManager.GetHubContext<PostsHub>().Clients.All.sendTeamListSend(ev.Teams);
            }
            return BadRequest(_apiErrorHandler.GenerateErrorDto(new ErrorList("Event Has already started or doesnt exist")));
        }
    }
}
