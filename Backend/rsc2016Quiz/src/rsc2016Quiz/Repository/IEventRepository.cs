using System.Collections.Generic;
using rsc2016Quiz.Models;

namespace rsc2016Quiz.Repository
{
    public interface IEventRepository
    {
        void Add(Event quiz);
        List<Event> GetEventsForUser(string userId);
    }
}
