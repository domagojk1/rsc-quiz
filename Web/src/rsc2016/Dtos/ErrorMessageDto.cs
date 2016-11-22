using System.Collections.Generic;
using rsc2016.ViewModels;

namespace rsc2016.Dtos
{
    public class ErrorMessageDto
    {
        public string Message { get; set; }
        public List<Error> Errors { get; set; } = new List<Error>();
        public int Code { get; set; }

        public ErrorMessageDto(List<Error> errors, int code)
        {
            Message = "Validation error";
            Errors = errors;
            Code = code;
        }

        public ErrorMessageDto(string message, int code)
        {
            Message = message;
            Code = code;
        }

        public ErrorMessageDto()
        {

        }




    }
}
