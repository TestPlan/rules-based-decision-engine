package exceptions;

/**
 * Created by shiv on 10/19/2016.
 */
public class ActionException extends Exception 
{
	private static final long serialVersionUID = 7672063404046384632L;
	//private String exception;


    //Constructor which takes a exception
    public ActionException(String exception)
    {
        super(exception);
    }

    public ActionException(Throwable clause)
    {
    	super(clause);
    }
    
    public ActionException(String exception, Throwable clause )
    {
    	super(exception, clause);
    }


}
