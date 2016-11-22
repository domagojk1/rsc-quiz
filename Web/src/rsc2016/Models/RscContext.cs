using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace rsc2016.Models
{
    public class RscContext : IdentityDbContext<User>
    {
        public RscContext(DbContextOptions<RscContext> options) : base(options)
        {
        }
    }
}