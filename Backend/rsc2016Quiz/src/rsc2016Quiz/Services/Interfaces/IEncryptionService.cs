namespace rsc2016Quiz.Services.Interfaces
{
    public interface IEncryptionService
    {

        string CreateSalt();
        string EncryptPassword(string password, string salt);
    }
}
