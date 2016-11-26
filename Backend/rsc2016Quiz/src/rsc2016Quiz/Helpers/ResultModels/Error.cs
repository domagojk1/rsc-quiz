using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace rsc2016Quiz.Helpers.ResultModels
{
    public class Error
    {
        public string Field { get; set; }
        public string Message { get; set; }

        public Error(string field, string message)
        {
            Field = field;
            Message = message;
        }
    }
}
