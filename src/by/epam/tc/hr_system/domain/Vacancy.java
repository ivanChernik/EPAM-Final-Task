package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;

public class Vacancy implements Serializable {
	
	public static final String OPEN_STATUS = "open";
	public static final String CLOSE_STATUS = "close";
	
	public static final String FULL_TIME = "full-time";
	public static final String PART_TIME = "part-time";

	private int id;
	private String name;
	private String descrption;
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

	public Vacancy(String name, String descrption, String shortDescription, String requirement, int salary,
			Date dateSubmission, String status, String companyName, String contactInformation, String employment) {
		this.name = name;
		this.descrption = descrption;
		this.shortDescription = shortDescription;
		this.requirement = requirement;
		this.salary = salary;
		this.dateSubmission = dateSubmission;
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

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
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

}
