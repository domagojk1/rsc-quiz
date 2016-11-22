using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using rsc2016.Dtos;

namespace rsc2016.Helpers
{
    public interface IApiErrorHandler
    {
        ErrorMessageDto GenerateErrorDto<T>(T model);
    }
}
