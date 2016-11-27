using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR.Infrastructure;
using rsc2016Quiz.Dtos;
using rsc2016Quiz.Repository;
using rsc2016Quiz.SignalR;

// For more information on enabling Web API for empty projects, visit http://go.microsoft.com/fwlink/?LinkID=397860

namespace rsc2016Quiz.Controllers
{
    [Route("api/[controller]")]
    public class QuestionsController : Controller
    {
        private readonly IQuestionRepository _questionRepository;
        private readonly IConnectionManager _connectionManager;
        private readonly IEventRepository _eventRepository;
        private readonly ITeamRepository _teamRepository;

        public QuestionsController(IQuestionRepository questionRepository, IConnectionManager connectionManager, IEventRepository eventRepository, ITeamRepository teamRepository)
        {
            _questionRepository = questionRepository;
            _connectionManager = connectionManager;
            _eventRepository = eventRepository;
            _teamRepository = teamRepository;
        }


        [HttpGet("GenerateQuestion/{id}")]
        public IActionResult GenerateQuestion(int id)
        {
            var teams = _teamRepository.GetTeamsByEventId(id);
            _connectionManager.GetHubContext<PostsHub>().Clients.All.sendTeamListSend(teams);
            var question =  _questionRepository.GenerateQuestion(id);
            if (question != null)
            {
                _connectionManager.GetHubContext<PostsHub>().Clients.All.sendNextQuestion(question);
                return Ok(question);

            }
            return BadRequest();
        }

        
        
        [HttpPost("SubmitQuestion/{id}")]
        public IActionResult SubmitQuestion(UserAnswerDto userAnswerDto,int id)
        {
            var question = _questionRepository.GetQuestionId(userAnswerDto.QuestionId);
            foreach (var answer in question.Answers)
            {
                if (answer.Correct && answer.Id == userAnswerDto.AnswerId)
                {
                   _teamRepository.IncrementTeam(userAnswerDto.TeamId);
                }
            }
            return Ok(new MessageDto("Done"));
        } 
        
    }
}
