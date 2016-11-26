using System.Threading.Tasks;
using rsc2016Quiz.Models;

namespace rsc2016Quiz.Repository
{
    public interface IUserRepository
    {
       
            Task<User> GetUserById(string id);
            Task<User> GetUserByName(string username);
            Task<User> GetUserByEmail(string email);
            void AddUser(User user);
        
    }
}
