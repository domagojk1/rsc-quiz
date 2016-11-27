using System.ComponentModel.DataAnnotations;

namespace rsc2016Quiz.Dtos.Authentication
{
    public class RegisterDto
    {
        [Required, MaxLength(256)]
        public string Username { get; set; }

        [Required]
        public string Password { get; set; }
    }
}
