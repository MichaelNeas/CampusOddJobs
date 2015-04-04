package uconn.campusoddjobs;

//import static org.junit.Assert.assertEquals; //this is for if you need it I guess? I didn't use it because I was working with Strings
import junit.framework.TestCase;


//import packageName.className; --> ex: controller.Message;

//No classes in junit.framework or junit.extensions
//See example test class at the bottom

/**
 * Test for {class name here}
 * 
 * @author Bri Boyce (bboyce@engineer.uconn.edu) ((<-- your info there))
 *
 */


public class TemplateToWriteATest extends TestCase
{
	//EXAMPLE!!
	//This is what I did for MessageSendTest
	//import junit.framework.TestCase;
	//nameOfRandomTest extends TestCase
	//public void test1() throws Exception --> I tested the send method
	//{
	//Test t = new Test(); --> Whatever class your testing goes here. i.e. Message m = new Message() etc.
	//t.someMethod(); --> I tested body method so m.body("test"); 
	//random variables if needed --> I needed boolean variables for my test so I created some in the test
	//t.someOtherMethod(variable); --> I tested the actual send method here so m.send(boolean1, boolean2).
	//}
	//public void test2() throws Exception --> Therese always had "throws Exception" which is why i have it...
	//etc
	//So basically just go through the class and write tests for methods that you can think up
	//For the homework we only need to do 3 tests so I'm pretty sure they can all be in the same test class
	//I wrote 3 tests for MessageSend so that's what I'm submitting
	//!!Make sure you add your test to the JUnitTestSuite class or it won't run!!
	//Just add where I already have MessageSentTest....same format but make sure to separate tests with commas!
	//Hope that is a slight bit helpful!
	
	//Example test case in java code
	
	public void example() throws Exception
	{
		//Test t = new Test();
		//t.randomMethod(2);
		//assertEquals(0, t.otherMethod());
	}
	
	public void anotherEx() throws Exception
	{
		//Test t = new Test();
		//t.aMethodMaybe("this is a random string of words just because"); //this test is just testing the if the input works with what the method is supposed to do
		
	}
	
	//you can have as many tests as you want!

}
