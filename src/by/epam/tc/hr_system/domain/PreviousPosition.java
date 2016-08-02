package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;

public class PreviousPosition implements Serializable{
	private String previousPosition;
	private String workDescription;
	private Date workFrom;
	private Date workTo;
	
	public PreviousPosition(){
		
	}

	public PreviousPosition(String previousPosition, String workDescription, Date workFrom, Date workTo) {
		super();
		this.previousPosition = previousPosition;
		this.workDescription = workDescription;
		this.workFrom = workFrom;
		this.workTo = workTo;
	}




	public String getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(String previousPosition) {
		this.previousPosition = previousPosition;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public Date getWorkFrom() {
		return workFrom;
	}

	public void setWorkFrom(Date workFrom) {
		this.workFrom = workFrom;
	}

	public Date getWorkTo() {
		return workTo;
	}

	public void setWorkTo(Date workTo) {
		this.workTo = workTo;
	}
	

}
