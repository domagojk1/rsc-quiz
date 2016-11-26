using System;
using System.ComponentModel.DataAnnotations;

namespace rsc2016Quiz.Dtos
{
    public class EventDto
    {

        public string Place { get; set; }
        public DateTime DateTime { get; set; }
        public string Description { get; set; }
        public int MaxMemberPerTeam { get; set; }
        public string UserId { get; set; }
    }
}
