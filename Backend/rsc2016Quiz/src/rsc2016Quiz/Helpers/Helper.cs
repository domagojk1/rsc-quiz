using System;

namespace rsc2016Quiz.Helpers
{
    public class Helper
    {
        public static double DatetimeToMiliseconds(DateTime dateTime)
        {
            return dateTime.ToUniversalTime().Subtract(
                new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc)
            ).TotalMilliseconds;
        }

        public static int GetRandomNumber(int maxNumber)
        {
            Random rnd = new Random();
            return rnd.Next(1, maxNumber);
        }
    }
}