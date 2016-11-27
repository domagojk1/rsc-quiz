using System.ComponentModel.DataAnnotations.Schema;

namespace rsc2016Quiz.Models
{
    public class Answer
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public bool Correct { get; set; }

        [ForeignKey("Question")]
        public int QuestionId { get; set; }
        public Question Question { get; set; }
    }
}
