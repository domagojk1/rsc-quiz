using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using rsc2016Quiz.Repository;

// For more information on enabling Web API for empty projects, visit http://go.microsoft.com/fwlink/?LinkID=397860

namespace rsc2016Quiz.Controllers
{
    [Route("api/[controller]")]
    public class QuestionsController : Controller
    {
        private readonly IQuestionRepository _questionRepository;

        public QuestionsController(IQuestionRepository questionRepository)
        {
            _questionRepository = questionRepository;
        }


        [HttpPost("{id}")]
        public IActionResult GenerateQuestion(int id)
        {
          var question =  _questionRepository.GenerateQuestion(id);
            if (question != null) return Ok(question);
            return BadRequest();
        }   
    }
}
