package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * JavaBean for previous position of applicant.
 * 
 * @author Ivan Chernikau
 *
 */
public class PreviousPosition implements Serializable {
	private String previousPosition;
	private String workDescription;
	private Date workFrom;
	private Date workTo;

	public PreviousPosition() {

	}

	public PreviousPosition(String previousPosition, String workDescription, Date workFrom, Date workTo) {
		super();
		this.previousPosition = previousPosition;
		this.workDescription = workDescription;
		this.workFrom = workFrom;
		this.workTo = workTo;
	}

	public PreviousPosition(String previousPosition, String workDescription) {
		super();
		this.previousPosition = previousPosition;
		this.workDescription = workDescription;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((previousPosition == null) ? 0 : previousPosition.hashCode());
		result = prime * result + ((workDescription == null) ? 0 : workDescription.hashCode());
		result = prime * result + ((workFrom == null) ? 0 : workFrom.hashCode());
		result = prime * result + ((workTo == null) ? 0 : workTo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreviousPosition other = (PreviousPosition) obj;
		if (previousPosition == null) {
			if (other.previousPosition != null)
				return false;
		} else if (!previousPosition.equals(other.previousPosition))
			return false;
		if (workDescription == null) {
			if (other.workDescription != null)
				return false;
		} else if (!workDescription.equals(other.workDescription))
			return false;
		if (workFrom == null) {
			if (other.workFrom != null)
				return false;
		} else if (!workFrom.equals(other.workFrom))
			return false;
		if (workTo == null) {
			if (other.workTo != null)
				return false;
		} else if (!workTo.equals(other.workTo))
			return false;
		return true;
	}

}
