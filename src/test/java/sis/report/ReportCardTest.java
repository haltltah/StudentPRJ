package sis.report; 
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set; 
import junit.framework.TestCase;
import sis.report.ReportCard;
import sis.studentinfo.*;

public class ReportCardTest extends TestCase {	
	ReportCard card;		
	
	@Override	public  void setUp() {		
		card = new ReportCard();	
	}		
	
	public void testCreate() {		
		assertEquals(ReportCard.A_MESSAGE, card.getMessage(Student.Grade.A));		
		assertEquals(ReportCard.B_MESSAGE, card.getMessage(Student.Grade.B));		
		assertEquals(ReportCard.C_MESSAGE, card.getMessage(Student.Grade.C));		
		assertEquals(ReportCard.D_MESSAGE, card.getMessage(Student.Grade.D));		
		assertEquals(ReportCard.F_MESSAGE, card.getMessage(Student.Grade.F));	
	}	
	
	/* The following three new tests for ReportCard demonstrate how to iterate through each  
	 * of the three possibilities. (These are unnecessary tests, and act more like language tests.)  
	 * Each of the three tests show different but valid testing approaches. */		
	
	/* The first test, testKeys, provides one technique for ensuring that you iterate through all 
	 * expected values in a collection. You first create a set and populating it with the objects 
	 * (grades in this example) you expect to receive. You then create a secondary set and add to it 
	 * each element that you iterate through. After iteration, you compare the two sets to ensure 
	 * that they are equal.* Perhaps you remember sets from grade school. (Remember Venn diagrams?) If not, no big deal. 
	 * A set is an unordered collection that contains unique elements. If you attempt to add a 
	 * duplicate element to a set, the set rejects it. In Java, the java.util.Set interface declares 
	 * the behavior of a set. The class java.util.HashSet is the workhorse implementation of the 
	 * Set interface. The HashSet class compares objects using equals to determine if a candidate 
	 * object is a duplicate.
	 *  Based on your understanding of how a hash table works, it should be clear that you cannot 
	 *  guarantee that its keys will appear in any specific order. It should also be clear that 
	 *  each key is unique. You cannot have two entries in a hash table with the same key. As such, 
	 *  when you send the message keySet to a HashMap object, it returns the keys to you as a set.
	 *   As shown in testKeys, you iterate a Set much as you iterate a List, using a for-each loop.
	 *   
	 */	
	
	public void testKeys() {		
		Set<Student.Grade> expectedGrades = new HashSet<Student.Grade>();		
		expectedGrades.add(Student.Grade.A);		
		expectedGrades.add(Student.Grade.B);		
		expectedGrades.add(Student.Grade.C);		
		expectedGrades.add(Student.Grade.D);		
		expectedGrades.add(Student.Grade.F);				
		Set<Student.Grade> grades = new HashSet<Student.Grade>();		
		for (Student.Grade grade: card.getMessages().keySet())			
			grades.add(grade);		
		assertEquals(expectedGrades, grades);	
	}	
	/* * Collection is an interface implemented by both HashSet and ArrayList. 
	 *  The Collection interface declares basic collection functionality, including the abilities  
	 *  to add objects to a collection and obtain an iterator from a collection. The Collection  
	 *  interface does not imply any order to a collection, so it does not declare methods related  
	 *  to indexed access as does the List interface. Both java.util.Set and java.util.List  
	 *  interfaces extend the Collection interface. 
	 * The test, testValues, uses a slightly different technique. You again create a Set to  
	 * represent expected values, then add to it each possible value. You obtain the hash table 
	 * values by sending the message values to the HashMap object.  
	 * As you iterate through the values, you verify that the list of expected values contains  
	 * each element. The contains method uses the equals method to determine if the collection 
	 * includes an object. Finally, you must also test the size of the secondary set  
	 * to ensure that it does not contain more elements than expected. 
	 * */		
	
	public void testValues() {		
		List<String> expectedMessages = new ArrayList<String>();		
		expectedMessages.add(ReportCard.A_MESSAGE);		
		expectedMessages.add(ReportCard.B_MESSAGE);		
		expectedMessages.add(ReportCard.C_MESSAGE);		
		expectedMessages.add(ReportCard.D_MESSAGE);		
		expectedMessages.add(ReportCard.F_MESSAGE);					
		Collection<String> messages = card.getMessages().values();		
		for (String message: messages)			
			assertTrue(expectedMessages.contains(message));		
		assertEquals(expectedMessages.size(), messages.size());	
	}	
	
	/* * The third test, testEntries, shows how you can iterate through the keys and associated  
	 * values (the maps) simultaneously. If you send the message entrySet to a HashMap,  
	 * it returns a Set of key-value pairs. You can iterate through this set using a for-each loop.  
	 * For each pass through the loop you get a Map.Entry reference that stores a key-value pair. 
	 * You can retrieve the key and value from a Map.Entry by using the methods getKey and getValue.  
	 *  The Map.Entry is bound to the same key type (Student.Grade in the example) and value type  
	 *  (String) as the HashMap object.	 */		
	
	public void testEntries() {		
		Set<Entry> expectedEntries = new HashSet<Entry>();		
		expectedEntries.add(new Entry(Student.Grade.A, ReportCard.A_MESSAGE));		
		expectedEntries.add(new Entry(Student.Grade.B, ReportCard.B_MESSAGE));		
		expectedEntries.add(new Entry(Student.Grade.C, ReportCard.C_MESSAGE));		
		expectedEntries.add(new Entry(Student.Grade.D, ReportCard.D_MESSAGE));		
		expectedEntries.add(new Entry(Student.Grade.F, ReportCard.F_MESSAGE));				
		Set<Entry> entries = new HashSet<Entry>();				
		for(Map.Entry<Student.Grade, String> entry: card.getMessages().entrySet())			
			entries.add(new Entry(entry.getKey(), entry.getValue()));					
		assertEquals(expectedEntries, entries);	
	}	
	
	class Entry {		
		private Student.Grade grade;		
		private String message;				
		Entry(Student.Grade grade, String message) {			
			this.grade = grade;			
			this.message = message;		
		}				
		
		@Override		
		public boolean equals(Object object) {			
			if (object.getClass() != this.getClass()) return false;			
			Entry that = (Entry)object;			
			return that.grade == this.grade &&				
				this.message.equals(that.message);		
		}				
		
		@Override		
		public int hashCode() {			
			final int hashMultiplier = 41;		    
			int result = 7;		    
			result = result * hashMultiplier + grade.hashCode();		    
			result = result * hashMultiplier + message.hashCode();		   
			return result;		 
		}	
	}
} 


