using System.Collections.Generic;
using rsc2016.ViewModels;

namespace rsc2016.Helpers.Models
{
    public class ErrorList
    {
        public ErrorList()
        {
            Errors = new List<Error>();
        }

        public ErrorList(string message)
        {
            Message = message;
        }

        public List<Error> Errors { get; set; } = new List<Error>();
        public string Message { get; set; }

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