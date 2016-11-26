using System.Collections.Generic;
using System.Linq;
using rsc2016Quiz.Models;
using rsc2016Quiz.Services.Interfaces;

namespace rsc2016Quiz.Repository
{
    public class EventRepository : IEventRepository
    {
        private readonly RscContext _context;

        public EventRepository(RscContext context)
        {
            _context = context;
        }

        public void Add(Event quiz)
        {
            _context.Events.Add(quiz);
            Commit();
        }

        public List<Event> GetEventsForUser(string userId)
        {
            return _context.Events.Where(e => e.UserId == userId).ToList();
        }

        private void Commit()
        {
            _context.SaveChanges();
        }

    }
}
