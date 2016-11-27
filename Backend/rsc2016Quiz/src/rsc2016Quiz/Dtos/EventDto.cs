using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using rsc2016Quiz.Models;

namespace rsc2016Quiz.Dtos
{
    public class EventDto
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Place { get; set; }
        public string DateTime { get; set; }
        public string Description { get; set; }
        public int MaxMemberPerTeam { get; set; }
        public string IsOpen { get; set; }
        public ICollection<Team> Teams { get; set; }
        public string UserId { get; set; }
    }
}
