namespace rsc2016Quiz.Helpers.ResultModels
{
    public class FileResult
    {
        public bool Status { get; set; }
        public string Path { get; set; }
        public string Message { get; set; }

        public FileResult(bool status, string path, string message)
        {
            Status = status;
            Path = path;
            Message = message;
        }

        public FileResult(bool status,string message)
        {
            Status = status;
            Message = message;
        }
        
    }
}
