namespace rsc2016.Dtos
{
    public class UserInfoDto
    {
        public string Id { get; set; }
        public string Username { get; set; }

        public UserInfoDto(string username, string id)
        {
            Username = username;
            Id = id;
        }
    }
}
