import java.io.*;
import java.net.*;

public class EmailSender{
	
   public static void main(String[] args) throws Exception
   {
      // Establish a TCP connection with the mail server.
      
	   Socket socket = new Socket("sbmta1.cc.stonybrook.edu",25);

	   // Create a BufferedReader to read a line at a time.
	   
      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);

      // Read greetings from the server.
      String response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("220")) {
         throw new Exception("220 reply not received from server.");
      }

   // Get a reference to the socket's output stream.
      OutputStream os = socket.getOutputStream();

      // Send HELO command and get server response.
      String command = "HELO alice\r\n";
      System.out.print(command);
      os.write(command.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
         throw new Exception("250 reply not received from server.");
      }

      // Send MAIL FROM command.
      command = "MAIL FROM: <alice@crepes.fr>\r\n";
      System.out.print(command);
      os.write(command.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
          throw new Exception("250 reply not received from server.");
       }
      
      // Send RCPT TO command.
      command = "RCPT TO: chloe.h.chen@stonybrook.edu\r\n";
      System.out.print(command);
      os.write(command.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
          throw new Exception("250 reply not received from server.");
       }
      
      // Send DATA command.
      command = "DATA\r\n";
      System.out.print(command);
      os.write(command.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);

      if (!response.startsWith("354")) {
          throw new Exception("354 reply not received from server.");
       }
      
      // Send message data.
      String data = "SUBJECT: greetings!\r\nHellooo,\r\n\nMy name is Alice! \r\n I'm sending an email from the command line, isn't"
      		+ " that fun?\r\nAnyways, goodnight\r\n";
      System.out.print(data);
      os.write(data.getBytes("US-ASCII"));
      
      // End with line with a single period.
      	data = ".\r\n";
        System.out.print(data);
        os.write(data.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
         }
      
      // Send QUIT command.
        command = "QUIT\r\n";
          System.out.print(command);
          os.write(command.getBytes("US-ASCII"));
          response = br.readLine();
          System.out.println(response);
          
      if (!response.startsWith("221")) {
          throw new Exception("221 reply not received from server.");
       }
      
   }
}



