package sis.report;

import java.util.*;
import sis.studentinfo.*;
import static sis.report.ReportConstant.NEWLINE;
public class CourseReport {
	
	private List<Session> sessions = new LinkedList<Session>();
	
	public void add(Session session) {
		sessions.add(session);
	}

	
	String text() {
		Collections.sort(sessions);
		StringBuilder buffer = new StringBuilder();
		
		for (Session session: sessions) {
			buffer.append(session.getDepartment() + " " + session.getNumber()+ NEWLINE);
		}
	return buffer.toString();
	}

}
