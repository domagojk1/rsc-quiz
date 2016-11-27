using Microsoft.EntityFrameworkCore.Metadata.Internal;
using System.ComponentModel.DataAnnotations.Schema;

namespace rsc2016Quiz.Models
{
    public class EventQuestion
    {
        [ForeignKey("Event")]
        public int EventId { get; set; }
        [ForeignKey("Question")]
        public int QuestionId { get; set; }

        public Event Event { get; set; }
        public Question Question { get; set; }
    }
}
