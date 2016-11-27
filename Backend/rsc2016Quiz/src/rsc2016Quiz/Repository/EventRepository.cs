using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
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

        public void OpenEvent(int id)
        {
            var evt = GetEventById(id);
            evt.IsOpen = true;
            _context.Update(evt);
            Commit();
        }

        public void CloseEvent(int id)
        {
            var evt = GetEventById(id);
            evt.IsOpen = false;
            _context.Update(evt);
            Commit();
        }

        public List<Event> GetEventsForUser(string userId)
        {
            return _context.Events.Include(e => e.Teams).Where(e => e.UserId == userId).ToList();
        }

        public List<Event> GetAllEvents()
        {
            return _context.Events.Include(e => e.Teams).ToList();
        }

        public Event GetEventById(int id)
        {
            return _context.Events.SingleOrDefault(t => t.Id == id);
        }

        public List<Event> GetEventByIdWithMembers(int id)
        {
            return _context.Events.Include(e => e.Teams).ThenInclude(t => t.TeamMembers).Where(e => e.Id == id).ToList();
        }


        private void Commit()
        {
            _context.SaveChanges();
        }


    }
}
