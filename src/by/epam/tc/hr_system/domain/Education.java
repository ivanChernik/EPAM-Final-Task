package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;

public class Education implements Serializable  {

	public static final String FULL_TIME = "Full-Time";
	public static final String PART_TIME = "Part-Time";
	public static final String DISTANT = "Distant";
 
	public static final String HIGHER = "Higher";
	public static final String AVERAGE = "Average";
	public static final String SPECIALIZED_SECONDARY = "Specialized-secondary";
	public static final String VOCATIONAL_TECHNICAL = "Vocational-technical";
	public static final String INCOMPLETE_HIGHER_EDUCATION = "Incomplete-higher-education";
	
	private String kindEducation;
	private String university;
	private String faculty;
	private String specialty;
	private String formEducation;
	private String educationDescription;
	private Date educationFrom;
	private Date educationTo;
	
	public Education(){
		
	}	

	public Education(String kindEducation, String university, String faculty, String specialty, String formEducation,
			String educationDescription, Date educationFrom, Date educationTo) {
		super();
		this.kindEducation = kindEducation;
		this.university = university;
		this.faculty = faculty;
		this.specialty = specialty;
		this.formEducation = formEducation;
		this.educationDescription = educationDescription;
		this.educationFrom = educationFrom;
		this.educationTo = educationTo;
	}



	public String getKindEducation() {
		return kindEducation;
	}

	public void setKindEducation(String kindEducation) {
		this.kindEducation = kindEducation;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getFormEducation() {
		return formEducation;
	}

	public void setFormEducation(String formEducation) {
		this.formEducation = formEducation;
	}

	public String getEducationDescription() {
		return educationDescription;
	}

	public void setEducationDescription(String educationDescription) {
		this.educationDescription = educationDescription;
	}

	public Date getEducationFrom() {
		return educationFrom;
	}

	public void setEducationFrom(Date educationFrom) {
		this.educationFrom = educationFrom;
	}

	public Date getEducationTo() {
		return educationTo;
	}

	public void setEducationTo(Date educationTo) {
		this.educationTo = educationTo;
	}

}
