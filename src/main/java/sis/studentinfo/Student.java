package sis.studentinfo;

import java.util.*;
import java.util.logging.Logger;

public class Student {
	private final  String name;
	private  String firstName = "";
	private  String lastName;
	private  String middleName = "";
	private  String id;
	final static int MAX_NAME_PARTS = 4;
	static final String TOO_MANY_NAME_PARTS_MSG =
		   "Student name '%s' contains more than %d parts";
	final static Logger logger = Logger.getLogger(Student.class.getName());
	
	private int credits;
	private String state = "";
	static final int CREDITS_REQUIERED_FOR_FULL_TIME = 12;
	static final String IN_STATE = "CO";
	private List<Grade> grades = new LinkedList<Grade>();
	public enum Grade {
		A(4), 
		B(3), 
		C(2), 
		D(1), 
		F(0);
		
		private int points;
		
		Grade(int points) {
			this.points = points;
		}
		
		int getPoints() {
			return points;
		}
	};
	
	public enum Flag {
		   ON_CAMPUS(1),
		   TAX_EXEMPT(2),
		   MINOR(4),
		   TROUBLEMAKER(8);

		   private int mask;

		   Flag(int mask) {
		      this.mask = mask;
		   }
		}

		private int settings = 0x0;
		
		public void set(Flag... flags) {
		   for (Flag flag: flags)
		      settings |= flag.mask;
		}

		public void unset(Flag... flags) {
		   for (Flag flag: flags)
		      settings &= ~flag.mask;
		}

		public boolean isOn(Flag flag) {
		   return (settings & flag.mask) == flag.mask;
		}

		public boolean isOff(Flag flag) {
		   return !isOn(flag);
		}

	
	
	private GradingStrategy gradingStrategy = new BasicGradingStrategy();
	/**
	 * <p>
	 * Creates a student object
	 * </p>
	 * @param fullName - String
	 */
	public Student (String fullName) {	
		this.name = fullName;
		credits = 0;
		List<String> nameParts = split(fullName);
		final int maximumNumberOfNameParts = 4;
		if (nameParts.size() > maximumNumberOfNameParts) {
		      String message =
		          String.format(Student.TOO_MANY_NAME_PARTS_MSG,
		        		  fullName, Student.MAX_NAME_PARTS);
		      Student.logger.info(message);
		      throw new StudentNameFormatException(message);
		}
		setName(nameParts);
	}
	


	/**
	 * <p>
	 * Split the full name in a list of Strings containing values for firstName, 
	 * middleName and lastName
	 * </p>
	 * @param fullName - String
	 * @return List<String>
	 */
	private List<String> split(String string) {
		List<String> results = new ArrayList<String>();
		
		StringBuffer word = new StringBuffer();
		int index = 0;
		while (index < string.length()) {
			if (!Character.isWhitespace(string.charAt(index))) {
				word.append(string.charAt(index));
			}
			else {
				if (word.length() > 0) {
					results.add(word.toString());
					word = new StringBuffer();
				}
			}
			index ++;
		}
		if (word.length() > 0) {
			results.add(word.toString());
		}
		return results;
	}

	/**
	 * <p>
	 * Set firstName, lastName, middleName
	 * </p>
	 * @param nameParts
	 */
	private void setName(List<String> list) {
		this.lastName = removeLast(list);
		if ( 0 != list.size()) {
			String name  = removeFirst(list);
			this.firstName = name;
				while (!list.isEmpty()) {
					name  = removeFirst(list);
					this.middleName = middleName  + " "  + name;
			}		
		}
		this.middleName = this.middleName.trim();
		
	}
	
	/**
	 * <p>
	 * Removes and returns first object from list or "" if list is empty
	 * </p>
	 * @param nameParts
	 * @return
	 */
	private String removeFirst(List<String> list) {
		if (list.isEmpty()) return "";
		return list.remove(0);
	}

	/**
	 * <p>
	 * Removes and returns last object from list or "" if list is empty
	 * </p>
	 * @param nameParts
	 * @return String
	 */
	private String removeLast(List<String> list) {
		if (list.isEmpty()) return "";
		return list.remove(list.size() - 1);
	}

	/**
	 * <p>
	 * return full name for Student
	 * </p>
	 * @return name - String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>
	 * return firstName for Student
	 * </p>
	 * @return firstName - String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * <p>
	 * return lastName for Student
	 * </p>
	 * @return lastName - String
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * <p>
	 * return midleName for Student
	 * </p>
	 * @return middleName - String
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	boolean isFullTime() {
		return credits >= CREDITS_REQUIERED_FOR_FULL_TIME;
	}
	
	int getCredits() {
		return credits;
	}
	
	void addCredits(int credits) {
		this.credits += credits;
	}
	
	boolean isInState() {
		return (state.toUpperCase()).equals(Student.IN_STATE);
	}
	
	void setState(String state) {
		this.state = state;
	}
	
	public void addGrade(Grade grade) {
		grades.add(grade);
	}
	
	double getGpa() {
		Student.logger.fine("begin getGpa " + System.currentTimeMillis());
		double sum = 0.0;
		if (grades.isEmpty()) return 0;
		for (Grade grade: grades) {		
			sum = sum + gradingStrategy.getGradePointsFor(grade);
		}
		Student.logger.fine("end getGpa " + System.currentTimeMillis());
		return sum/grades.size();
	}
	
/*	private double gradePointsFor(Grade grade) {
		double points = basicGradePointsFor(grade);
		if (isHonors) {
			if (points > 0) {
				points +=1;
			}
		}
		return points;
	}*/
	
	void setGradingStrategy(GradingStrategy gradingStrategy) {
		this.gradingStrategy = gradingStrategy;
	}

	/**
	 * <p>
	 * Set student's id
	 * </p>
	 * @param id - String
	 */
	public void setId(String id) {
		this.id = id;
		
	}

	/**
	 * <p>
	 * Get the student's id
	 * </p>
	 * @return String
	 */
	public String getId() {
		return this.id;
	}
/*	
 * The optional finally block ensures that the Java VM always executes some piece of code, 
 * regardless of whether or not an exception was thrown. You may attach a single finally 
 * block to the end of a try-catch block.
 */
	/*
	public static Student findByLastName(String lastName)
    throws RuntimeException {
		java.sql.Connection dbConnection = null;
		try {
			dbConnection = getConnection();
			return lookup(dbConnection, lastName);
		}
		catch (java.sql.SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		finally {
			close(dbConnection);
		}
	}
	*/
	
	//If you supply a finally block, the catch block itself is optional. 
	/*
	public static Student findByLastName(String lastName)
      throws java.sql.SQLException {
   		java.sql.Connection dbConnection = null;
   		try {
      		dbConnection = getConnection();
      		return lookup(dbConnection, lastName);
  		}
   		finally {
      		close(dbConnection);
   		}
	}
	 */
}
