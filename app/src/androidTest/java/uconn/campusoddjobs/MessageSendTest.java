package uconn.campusoddjobs;

//import model.Message;
//import junit.framework.TestCase;
//
///**
// * Sending test for Message class
// *
// * @author Brianna Boyce (bboyce@engineer.uconn.edu)
// *
// */
//
//public class MessageSendTest extends TestCase
//{
//	public void testSendingMessage() throws Exception
//	{
//		Message m = new Message();
//		String message;
//		message = m.body("test");
//		boolean save = false;
//		boolean show = false;
//		m.send(save, show, message);
//
//	}
//
//	public void testFilter() throws Exception
//	{
//		Message m = new Message();
//		String filtered;
//		filtered = m.checkString(m.body("test"));
//		m.filter(filtered);
//
//	}
//
//	public void testSentConfirmation() throws Exception
//	{
//		Message m = new Message();
//		boolean save = false;
//		boolean show = true;
//		boolean sent;
//		sent = m.send(save, show, "test");
//		m.confirmation(sent);
//	}
//
//}
