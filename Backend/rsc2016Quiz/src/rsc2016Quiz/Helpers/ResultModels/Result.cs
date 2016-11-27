using System.Collections.Generic;

namespace rsc2016Quiz.Helpers.ResultModels
{
    public class Result
    {
        public bool Status { get; set; }
        public string Message { get; set; }
        public List<Error> Errors { get; set; } = new List<Error>();

        public Result(bool status, string message, List<Error> errors)
        {
            Status = status;
            Message = message;
            Errors = errors;
        }

        //
        public Result(ErrorList errorList)
        {
            Status = false;
            if (errorList.Errors.Count == 0)
                Message = errorList.Message;
            else Errors = errorList.Errors;
        }

        public Result(string message)
        {
            Status = false;
            Message = message;
        }

        public Result()
        {
            Status = true;
            Message = "";
        }
    }
}