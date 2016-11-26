using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DiagnosticAdapter.Internal;

namespace rsc2016Quiz.Models
{
    public class RscContext : IdentityDbContext<User>
    {
        public DbSet<Team> Teams { get; set; }
        public DbSet<Event> Events { get; set; }
        public DbSet<TeamMember> TeamMembers { get; set; }

        public RscContext(DbContextOptions<RscContext> options) : base(options)
        {
            Database.Migrate();
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<TeamMember>()
                .HasKey(pf => new {pf.TeamId, pf.UserId});


            base.OnModelCreating(modelBuilder);
        }
    }
}
