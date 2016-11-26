namespace rsc2016Quiz.Models
{
    public class Question
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public QuestionType Type { get; set; }
    }

    public enum QuestionType
    {
        SingleAnswer,
        MultipleAnswer,
        ManualInput,
        TrueFalse
    }
}
