package ie.gmit.sw.queues;

import java.util.*;
import java.util.concurrent.*;

public class VigenereRequestManager
{
	private static final int maxCapacity = 10;
	private BlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(maxCapacity);
	private Map<Long, String> out = new ConcurrentHashMap<Long, String>();
	private VigenereHandler vHandler;
	private String cypherText;
	
	public VigenereRequestManager()
	{
	}
	public void add(final Request req)
	{	
		try
		{
			//Because it's a BlockingQueue, it will put the thread to sleep
			//dont want to put the whole program to sleep
			//so create a new thread so it will only put that one to sleep and others can keep going.
			Thread t1 = new Thread(new Runnable()
			{
				public void run()
				{
					try
					{
						//add to queue
						queue.put(req);
						//Puts to map with encoded text
						out.put(req.getJobNumber(),req.getCypherText());				
						vHandler = new VigenereHandler(queue, out);
						//Changes the text in the map to the result vHandler puts out
						out.put(req.getJobNumber(), vHandler.returnResult());
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
			});
			t1.start();
			//Give the thread a chance to run.
			t1.join();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public String getResult(long jobNumber) throws Exception
	{
		Thread t2 = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					String result =	out.get(jobNumber);
					cypherText = result;
					System.out.println(cypherText);
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});
		t2.start();
		t2.join();
		//When the request is complete remove the job from the map
		out.remove(jobNumber);
		vHandler.removeRequest(jobNumber);
		return cypherText;
	}
	
	public static void main(String[] args) throws Exception 
	{
		// Testing to see if adding request and getting results work.
		Request req = new Request("TGMBXLMTUEBLAFXGMTKBTGBLF", 5, 1);
		VigenereRequestManager vrm = new VigenereRequestManager();
		vrm.add(req);
		System.out.println(vrm.getResult(1));
	}
}