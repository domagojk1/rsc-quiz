﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR.Infrastructure;
using rsc2016Quiz.Dtos;
using rsc2016Quiz.Models;
using rsc2016Quiz.Repository;
using rsc2016Quiz.SignalR;
using Microsoft.AspNetCore.SignalR.Hubs;
using rsc2016Quiz.Helpers;
using rsc2016Quiz.Helpers.ResultModels;

// For more information on enabling Web API for empty projects, visit http://go.microsoft.com/fwlink/?LinkID=397860

namespace rsc2016Quiz.Controllers
{
    [Route("api/[controller]")]
    public class EventsController : Controller
    {
        private readonly IEventRepository _eventRepository;
        private readonly IUserRepository _userRepository;
        private readonly IConnectionManager _connectionManager;
        private readonly IApiErrorHandler _apiErrorHandler;

        public EventsController(IEventRepository eventRepository, IUserRepository userRepository, IConnectionManager connectionManager, IApiErrorHandler apiErrorHandler)
        {
            _eventRepository = eventRepository;
            _userRepository = userRepository;
            _connectionManager = connectionManager;
            _apiErrorHandler = apiErrorHandler;
        }

        [HttpGet("MyEvents")]
        [Produces(typeof(ICollection<Event>))]
        [Authorize("Bearer")]
        public IActionResult GetUserEvents()
        {
            var userName = User.Identity.Name;
            var user = _userRepository.GetUserByEmail(userName);
            return Ok(Mapper.Map<List<EventDto>>(_eventRepository.GetEventsForUser(user.Result.Id)));
        }

        [Authorize("Bearer")]
        [HttpPost("Add")]
        [Produces(typeof(MessageDto))]
        public IActionResult AddEvent([FromBody] EventDto quiz)
        {
            var userName = User.Identity.Name;
            var user = _userRepository.GetUserByEmail(userName);
            quiz.UserId = user.Result.Id;
            _eventRepository.Add(Mapper.Map<Event>(quiz));
            var events = _eventRepository.GetAllEvents();
            _connectionManager.GetHubContext<PostsHub>().Clients.All.sendQuizList(events);
            return Ok(new MessageDto("Successfull"));
        }

        [HttpPost("AddMessage")]
        public IActionResult AddMessage()
        {
            Post post = new Post();
            
            return Ok(new MessageDto("ok"));
        }

        [Authorize("Bearer")]
        [HttpPost("StartEvent/{id}")]
        public IActionResult StartEvent(int id)
        {
           var evt =
            _eventRepository.GetEventById(id);
            if (evt != null)
            {
                _eventRepository.CloseEvent(id);
                _connectionManager.GetHubContext<PostsHub>().Clients.All.startQuiz("Quiz has started");
                return Ok(evt);
            }
            return BadRequest(_apiErrorHandler.GenerateErrorDto(new ErrorList("Quiz with that id doesnt exist")));
        }

        [HttpGet("")]
        public IActionResult GetAllEvents()
        {
            var result = _eventRepository.GetAllEvents();
            return Ok(Mapper.Map<List<Event>>(result));
        }

        [HttpGet("OpenEvent/{id}")]
        public IActionResult OpenEvent(int id)
        {

            var result = _eventRepository.GetEventById(id);
            if (result != null)
            {
                _eventRepository.OpenEvent(id);
            }
            return Ok(Mapper.Map<EventDto>(result));
        }

        [HttpGet("GetTeams/{id}")]
        public IActionResult GetEventTeam(int id)
        {

            var result = _eventRepository.GetEventById(id);
            if (result != null)
            {
                return Ok(Mapper.Map<EventDto>(result));
            }
            return BadRequest(_apiErrorHandler.GenerateErrorDto(new ErrorList("Team with that id doesnt exist")));
        }
    }
}