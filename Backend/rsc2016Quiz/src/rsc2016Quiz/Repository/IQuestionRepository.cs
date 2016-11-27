using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using rsc2016Quiz.Models;

namespace rsc2016Quiz.Repository
{
    public interface IQuestionRepository
    {
        void Add(Event quiz);
        Question GenerateQuestion(int eventId);
        Question GetQuestionId(int id);
    }
}
