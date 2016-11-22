using rsc2016.Helpers.Models;

namespace rsc2016.Helpers
{
    public class ResultManager
    {
        public static Result Success()
        {
            return new Result();
        }

        public static Result ModelFailed(ErrorList errorList)
        {
            return new Result(errorList);
        }

        public static Result Failed(string message)
        {
            return new Result(message);
        }
    }
}