public class InterviewMindTestException {

    public static void main(String[] args) {



        InterviewMindTestException m = new InterviewMindTestException();
        m.a();



    }
    void a()
    {
        try{
            System.out.println("a(): Main called");
            b();
        }catch(Exception e)
        {
            System.out.println("Exception is caught");
        }
    }
    void b() throws Exception
    {
        try{
            System.out.println("b(): Main called");
            c();
        }catch(Exception e){
            throw new Exception();
        }
        finally
        {
            System.out.println("finally block is called");
        }
    }
    void c() throws Exception
    {
        throw new Exception();
    }
}
/**
 *
 * output:--
 *
 *
 * a(): Main called
 * b(): Main called
 * finally block is called
 * Exception is caught
 */