using System;

namespace rsc2016.Helpers
{
    public class Helper
    {
        public static double DatetimeToMiliseconds(DateTime dateTime)
        {
            return dateTime.ToUniversalTime().Subtract(
                new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc)
            ).TotalMilliseconds;
        }
    }
}