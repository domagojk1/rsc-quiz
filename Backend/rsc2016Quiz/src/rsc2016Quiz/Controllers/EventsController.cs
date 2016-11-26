using System;
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

// For more information on enabling Web API for empty projects, visit http://go.microsoft.com/fwlink/?LinkID=397860

namespace rsc2016Quiz.Controllers
{
    [Route("api/[controller]")]
    public class EventsController : Controller
    {
        private readonly IEventRepository _eventRepository;
        private readonly IUserRepository _userRepository;
        private readonly IConnectionManager _connectionManager;

        public EventsController(IEventRepository eventRepository, IUserRepository userRepository, IConnectionManager connectionManager)
        {
            _eventRepository = eventRepository;
            _userRepository = userRepository;
            _connectionManager = connectionManager;
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

        [Authorize]
        [HttpPost("Add")]
        [Produces(typeof(MessageDto))]
        public IActionResult AddEvent([FromBody] EventDto quiz)
        {
            var userName = User.Identity.Name;
            var user = _userRepository.GetUserByEmail(userName);
            quiz.UserId = user.Result.Id;
            _eventRepository.Add(Mapper.Map<Event>(quiz));
            return Ok(new MessageDto("Successfull"));
        }

        [HttpPost("AddMessage")]
        public IActionResult AddMessage()
        {
            Post post = new Post();
            post.UserName = "nekwj";
            post.Text = "josnekej";
            
            Startup.ConnectionManager.GetHubContext<PostsHub>().Clients.All.send("post");
            _connectionManager.GetHubContext<PostsHub>().Clients.All.broadcastMessage("post");
            _connectionManager.GetHubContext<PostsHub>().Clients.All.BroadcastMessage("post2");
            _connectionManager.GetHubContext<PostsHub>().Clients.All.publishPost("nekaj");
            _connectionManager.GetHubContext<PostsHub>().Clients.All.send("post");
            return Ok(_connectionManager.GetHubContext<PostsHub>().Clients.All);
        }
    }
}
