using System.Collections.Generic;

namespace rsc2016Quiz.Helpers.ResultModels
{
    public class ErrorList
    {
        public List<Error> Errors { get; set; } = new List<Error>();
        public string Message { get; set; }
        public ErrorList()
        {
            Errors = new List<Error>();
        }

        public ErrorList(string message)
        {
            Message = message;
        }


        public void AddError(Error error)
        {
            Errors.Add(error);
        }

        public bool IsValid()
        {
            if ((Message != null) || (Errors.Count > 0))
                return false;
            return true;
        }
    }
}