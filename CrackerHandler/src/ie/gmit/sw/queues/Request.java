package ie.gmit.sw.queues;

public class Request 
{
	private String cypherText;
	private int maxKeySize;
	private long jobNumber;
	
	public Request(String cText, int maxNo, long jobNo)
	{
		//Takes in the cypher text, max Key length and the job number for a request object
		setCypherText(cText);
		setMaxKeySize(maxNo);
		setJobNumber(jobNo);
	}

	public String getCypherText()
	{
		return cypherText;
	}

	public void setCypherText(String cypherText) 
	{
		this.cypherText = cypherText;
	}

	public int getMaxKeySize()
	{
		return maxKeySize;
	}

	public void setMaxKeySize(int maxKeySize)
	{
		this.maxKeySize = maxKeySize;
	}

	public long getJobNumber()
	{
		return jobNumber;
	}

	public void setJobNumber(long jobNumber) 
	{
		this.jobNumber = jobNumber;
	}
	
}
