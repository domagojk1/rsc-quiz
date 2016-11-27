using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using rsc2016Quiz.Models;

namespace rsc2016Quiz.Repository
{
    public class TeamRepository : ITeamRepository
    {
        private readonly RscContext _context;

        public TeamRepository(RscContext context, IUserRepository userRepository)
        {
            _context = context;
        }

        public void Add(Team team)
        {
            _context.Teams.Add(team);
            Commit();

        }

        public Team GetTeamById(int id)
        {
            return _context.Teams.Include(t => t.TeamMembers).SingleOrDefault(t => t.Id == id);
        }

        public  List<Team> GetTeamsByEventId(int eventId)
        {
            return _context.Teams.Where(t => t.EventId == eventId).ToList();
        }

        public void AddMememberToTeam(int id,User user )
        {
            var team = GetTeamById(id);
            foreach (var member in team.TeamMembers)
            {
                if (member.Id == user.Id) return;
            }
            TeamMember teamMember = new TeamMember()
            {
                TeamId = id,
                UserId = user.Id
            };
            _context.TeamMembers.Add(teamMember);
            Commit();
        }

        public void IncrementTeam(int id)
        {
            var team = GetTeamById(id);
            if (team != null)
            {
                team.Score++;
                _context.Teams.Update(team);
                Commit();
            }
            

        }

        private void Commit()
        {
            _context.SaveChanges();
        }
    }
}
