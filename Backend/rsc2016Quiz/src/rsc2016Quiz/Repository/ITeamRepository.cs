using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using rsc2016Quiz.Models;

namespace rsc2016Quiz.Repository
{
    public interface ITeamRepository
    {
        void Add(Team team);
        Team GetTeamById(int id);
        List<Team> GetTeamsByEventId(int eventId);
        void AddMememberToTeam(int id,User user );
        void IncrementTeam(int id);
    }
}
