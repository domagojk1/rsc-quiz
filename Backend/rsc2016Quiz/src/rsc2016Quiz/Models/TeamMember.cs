using System.ComponentModel.DataAnnotations.Schema;

namespace rsc2016Quiz.Models
{
    public class TeamMember
    {
        [ForeignKey("Team")]
        public int TeamId { get; set; }

        [ForeignKey("User")]
        public string UserId { get; set; }

        public Team Team { get; set; }
        public User User { get; set; }
    }
}
