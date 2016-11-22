using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using rsc2016.Helpers.Models;
using rsc2016.ViewModels;

namespace rsc2016.Helpers
{
    public class ModelStateValidator<T>
    {
        public static List<ValidationResult> CheckIfModelStateIsValid(T model)
        {
            var context = new ValidationContext(model, null, null);
            var results = new List<ValidationResult>();

            var smth = Validator.TryValidateObject(model, context, results, true);
            return results;
        }

        public static ErrorList GetModelErrors(T model)
        {
            var result = CheckIfModelStateIsValid(model);
            var errorList = new ErrorList();
            foreach (var error in result)
            {
                if (!error.MemberNames.Any())
                    return new ErrorList(error.ErrorMessage);
                errorList.AddError(new Error(error.MemberNames.First(), error.ErrorMessage));
            }
            return errorList;
        }
    }
}