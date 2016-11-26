using System.ComponentModel.DataAnnotations;

namespace rsc2016Quiz.Dtos.Authentication
{
    public class LoginDto
    {
        [Required]
        public string id { get; set; }

        public string name { get; set; }

    }
}
