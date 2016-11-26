using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using rsc2016Quiz.Dtos;
using rsc2016Quiz.Models;
using rsc2016Quiz.Repository;

// For more information on enabling Web API for empty projects, visit http://go.microsoft.com/fwlink/?LinkID=397860

namespace rsc2016Quiz.Controllers
{
    [Route("api/[controller]")]
    public class EventsController : Controller
    {
        private readonly IEventRepository _eventRepository;
        private readonly IUserRepository _userRepository;

        public EventsController(IEventRepository eventRepository, IUserRepository userRepository)
        {
            _eventRepository = eventRepository;
            _userRepository = userRepository;
        }

        [HttpGet("MyEvents")]
        [Produces(typeof(ICollection<Event>))]
        [Authorize("Bearer")]
        public IActionResult GetUserEvents()
        {
            var userName = User.Identity.Name;
            var user = _userRepository.GetUserByName(userName);
            return Ok(Mapper.Map<List<EventDto>>(_eventRepository.GetEventsForUser(user.Result.Id)));
        }


        [HttpPost("Add")]
        [Produces(typeof(MessageDto))]
        public IActionResult AddEvent(EventDto quiz)
        {
            var userName = User.Identity.Name;
            var user = _userRepository.GetUserByName(userName);
            quiz.UserId = user.Result.Id;
            _eventRepository.Add(Mapper.Map<Event>(quiz));
            return Ok(new MessageDto("Successfull"));
        }
    }
}
