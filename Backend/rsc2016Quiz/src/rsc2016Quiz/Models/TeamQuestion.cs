using Microsoft.EntityFrameworkCore.Metadata.Internal;
using System.ComponentModel.DataAnnotations.Schema;

namespace rsc2016Quiz.Models
{
    public class TeamQuestion
    {
        [ForeignKey("Team")]
        public int TeamId { get; set; }

        [ForeignKey("Question")]
        public int QuestionId { get; set; }

        public Question Question { get; set; }
        public Team Team { get; set; }
    }
}
