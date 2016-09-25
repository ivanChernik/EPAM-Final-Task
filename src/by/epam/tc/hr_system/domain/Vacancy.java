package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * JavaBean for vacancy.
 * 
 * @author Ivan Chernikau
 *
 */
public class Vacancy implements Serializable {
	
	public static final String OPEN_STATUS = "open";
	public static final String CLOSE_STATUS = "close";
	
	public static final String FULL_TIME = "full-time";
	public static final String PART_TIME = "part-time";
	
	private static List<String> statusList = Arrays.asList(OPEN_STATUS,CLOSE_STATUS);
	private static List<String> timeList = Arrays.asList(FULL_TIME,PART_TIME);

	private int id;
	private String name;
	private String description;
	private String shortDescription;
	private String requirement;
	private int salary;
	private Date dateSubmission;
	private String status;
	private String companyName;
	private String contactInformation;
	private String employment;

	public Vacancy() {

	}

	public Vacancy(String name, String description, String shortDescription, String requirement, int salary,
			Date dateSubmission, String status, String companyName, String contactInformation, String employment) {
		this.name = name;
		this.description = description;
		this.shortDescription = shortDescription;
		this.requirement = requirement;
		this.salary = salary;
		this.dateSubmission = dateSubmission;
		this.status = status;
		this.companyName = companyName;
		this.contactInformation = contactInformation;
		this.employment = employment;
	}
	
	

	public Vacancy(int id, String name, String description, String shortDescription, String requirement, int salary,
			String status, String companyName, String contactInformation, String employment) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.shortDescription = shortDescription;
		this.requirement = requirement;
		this.salary = salary;
		this.status = status;
		this.companyName = companyName;
		this.contactInformation = contactInformation;
		this.employment = employment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getDateSubmission() {
		return dateSubmission;
	}

	public void setDateSubmission(Date dateSubmission) {
		this.dateSubmission = dateSubmission;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public static List<String> getStatusList() {
		return statusList;
	}

	public static List<String> getTimeList() {
		return timeList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((contactInformation == null) ? 0 : contactInformation.hashCode());
		result = prime * result + ((dateSubmission == null) ? 0 : dateSubmission.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employment == null) ? 0 : employment.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((requirement == null) ? 0 : requirement.hashCode());
		result = prime * result + salary;
		result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Vacancy other = (Vacancy) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (contactInformation == null) {
			if (other.contactInformation != null)
				return false;
		} else if (!contactInformation.equals(other.contactInformation))
			return false;
		if (dateSubmission == null) {
			if (other.dateSubmission != null)
				return false;
		} else if (!dateSubmission.equals(other.dateSubmission))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employment == null) {
			if (other.employment != null)
				return false;
		} else if (!employment.equals(other.employment))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (requirement == null) {
			if (other.requirement != null)
				return false;
		} else if (!requirement.equals(other.requirement))
			return false;
		if (salary != other.salary)
			return false;
		if (shortDescription == null) {
			if (other.shortDescription != null)
				return false;
		} else if (!shortDescription.equals(other.shortDescription))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
}
