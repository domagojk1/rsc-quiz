using System.ComponentModel.DataAnnotations.Schema;

namespace rsc2016Quiz.Models
{
    public class QuestionEvent
    {
        [ForeignKey("Question")]
        public int QuestionId { get; set; }
        [ForeignKey("Event")]
        public int EventId { get; set; }

        public Question Question { get; set; }
        public Event Event { get; set; }
    }
}
