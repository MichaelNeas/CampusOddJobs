package uconn.campusoddjobs;
//
//import model.ManipulateKarma;
//import storage.JobObject;
//import storage.UserObject;
//import junit.framework.TestCase;
///**
// * Tests to give, remove karma, and assign jobs
// *
// * @author Michael Neas
// *
// */
//public class KarmaExchangeTest extends TestCase
//{
//	public void testAddingKarma() throws Exception
//	{
//		UserObject person1 = new UserObject("name1", "pw", "coolpic", "Storrs", "Harford", "06269");
//		UserObject person2 = new UserObject("name2", "pw", "coolerpic", "waterbury", "watertown", "24512");
//		person1.karma = 100;
//		person2.karma = 200;
//		ManipulateKarma testManipulation = new ManipulateKarma();
//		testManipulation.addJobCompletionKarma(person1, person2);
//		testManipulation.addPaidKarma(person2, person1);
//		testManipulation.addPostingKarma(person1, person2);
//		//System.out.println(person1.karma + " " + person2.karma);
//	}
//
//	public void testRemovingKarmaForNoPayment() throws Exception
//	{
//		UserObject person1 = new UserObject("name1", "pw", "coolpic", "Storrs", "Harford", "06269");
//		UserObject person2 = new UserObject("name2", "pw", "coolerpic", "waterbury", "watertown", "24512");
//		person1.karma = 300;
//		person2.karma = 400;
//		ManipulateKarma testManipulation = new ManipulateKarma();
//		testManipulation.removeBadJobKarma(person1, person2);
//		testManipulation.removePostingKarma(person1, person2);
//		testManipulation.removeUnpaidKarma(person2, person1);
//		//System.out.println(person1.karma + " " + person2.karma);
//	}
//	public void testNewUser() throws Exception
//	{
//		UserObject person1 = new UserObject("name1", "pw", "coolpic", "Storrs", "Harford", "06269");
//		UserObject person2 = new UserObject("name2", "pw", "coolerpic", "waterbury", "watertown", "24512");
//		UserObject person3 = new UserObject();
//		UserObject person4 = new UserObject("guy@uconn.edu");
//		person1.setPassword("newPassword");
//		person2.setCity("newCity");
//		JobObject job = new JobObject("Computer Problems","Heeeeelllllpppppppppppp",person1, false);
//		person3.addNewJob(job);
//		person4.setUserID(3);
//	}
//}
