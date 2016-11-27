using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations.Schema;

namespace rsc2016Quiz.Models
{
    public class Team
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Password { get; set; }

        [DefaultValue(0)]
        public int Score { get; set; }

        [DefaultValue(false)]
        public bool IsWinner { get; set; }

        [ForeignKey("Event")]
        public int EventId { get; set; }
        public Event Event { get; set; }

        public ICollection<User> TeamMembers { get; set; } = new List<User>();
    }
}
