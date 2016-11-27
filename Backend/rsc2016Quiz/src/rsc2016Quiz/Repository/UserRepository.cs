using System.Threading.Tasks;
using rsc2016Quiz.Models;
using Microsoft.EntityFrameworkCore;

namespace rsc2016Quiz.Repository
{
    public class UserRepository : IUserRepository
    {
        private readonly RscContext _context;

        public UserRepository(RscContext context)
        {
            _context = context;
        }


        public async Task<User> GetUserById(string id)
        {

            return await _context.Users.SingleOrDefaultAsync(u => u.Id == id);
        }

        public async Task<User> GetUserByName(string username)
        {
            return await _context.Users.SingleOrDefaultAsync(u => u.UserName == username);
        }

        public async Task<User> GetUserByEmail(string email)
        {
            return await _context.Users.SingleOrDefaultAsync(u => u.Email == email);
        }

        public void AddUser(User user)
        {

            _context.Add(user);
            _context.SaveChanges();
            Commit();
        }

        private void Commit()
        {
            _context.SaveChanges();
        }
    }
}
