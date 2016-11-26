using rsc2016Quiz.Dtos;

namespace rsc2016Quiz.Helpers
{
    public interface IApiErrorHandler
    {
        ErrorMessageDto GenerateErrorDto<T>(T model);
    }
}
