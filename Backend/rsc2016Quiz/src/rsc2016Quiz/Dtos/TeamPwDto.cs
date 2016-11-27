namespace rsc2016Quiz.Dtos
{
    public class TeamPwDto
    {
        public string Password { get; set; }
        public int TeamId { get; set; }
        public int EventId { get; set; }

        public TeamPwDto(string password,int teamId,int eventId)
        {
            TeamId = teamId;
            Password = password;
            EventId = eventId;
        }
    }
}
