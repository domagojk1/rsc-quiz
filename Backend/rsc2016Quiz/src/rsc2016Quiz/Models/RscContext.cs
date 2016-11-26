using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace rsc2016Quiz.Models
{
    public class RscContext : IdentityDbContext<User>
    {

        public RscContext(DbContextOptions<RscContext> options) : base(options)
        {
            Database.Migrate();
        }
    }
}
