package ie.gmit.sw;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import ie.gmit.sw.queues.Request;
import ie.gmit.sw.queues.VigenereRequestManager;

public class CrackerHandler extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String remoteHost = null;
	private static long jobNumber = 0;
	private VigenereRequestManager vrm = new VigenereRequestManager();
	
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		int maxKeyLength = Integer.parseInt(req.getParameter("frmMaxKeyLength"));
		String cypherText = req.getParameter("frmCypherText");
		String taskNumber = req.getParameter("frmStatus");
		String result = "Please wait...";
		String resultCheck = "Please wait...";

		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		if (taskNumber == null){
			//Add job to in-queue
			taskNumber = new String("T" + jobNumber);
			jobNumber++;	
			Request newReq = new Request(cypherText, maxKeyLength, jobNumber);
			vrm.add(newReq);
			try {
				result = vrm.getResult(jobNumber);
				System.out.println(jobNumber);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Check out-queue for finished job
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		
		out.print("RMI Server is located at " + remoteHost);
		out.print("<P>Maximum Key Length: " + maxKeyLength);		
		out.print("<P>CypherText: " + cypherText);
		out.print("<P>Result: " + result);

		
		out.print("<form name=\"frmCracker\">");
		out.print("<input name=\"frmMaxKeyLength\" type=\"hidden\" value=\"" + maxKeyLength + "\">");
		out.print("<input name=\"frmCypherText\" type=\"hidden\" value=\"" + cypherText + "\">");
		out.print("<input name=\"frmStatus\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		if(result.equals(resultCheck))
		{
			out.print("<script>");
			out.print("var wait=setTimeout(\"document.frmCracker.submit();\", 10000);");	
			out.print("</script>");
		}	
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
	
	public static void main(String[] args) {
		System.out.println("Compiling");
	}

}