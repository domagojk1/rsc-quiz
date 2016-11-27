using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using rsc2016Quiz.Models;

namespace rsc2016Quiz.Repository
{
    public class QuestionRepository : IQuestionRepository
    {
        private readonly RscContext _context;

        public QuestionRepository(RscContext context)
        {
            _context = context;
        }

        public void Add(Event quiz)
        {
            _context.Events.Add(quiz);
            Commit();
        }

        public Question GetQuestionId(int id)
        {
            return _context.Questions.Include(q => q.Answers).SingleOrDefault(q => q.Id == id);
        }

        public Question GenerateQuestion(int eventId)
        {
            var question = _context.Questions.ToList().First();
            var eventQuestion = new EventQuestion()
            {
                EventId = eventId,
                QuestionId = question.Id
            };
            _context.EventQuestions.Add(eventQuestion);
            Commit();

            return GetQuestionId(question.Id);

        }

        private void Commit()
        {
            _context.SaveChanges();
        }
    }
}
