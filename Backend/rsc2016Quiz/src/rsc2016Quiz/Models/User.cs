using System;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;

namespace rsc2016Quiz.Models
{
    public class User : IdentityUser
    {
        public string Salt { get; set; }
        public DateTime Created { get; set; }
    }
}
