package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class Education implements Serializable {

	public static final String FULL_FORM = "Full-form";
	public static final String PART_FORM = "Part-form";
	public static final String DISTANT_FORM = "Distant-form";

	public static final String HIGHER = "Higher";
	public static final String AVERAGE = "Average";
	public static final String SPECIALIZED_SECONDARY = "Specialized-secondary";
	public static final String VOCATIONAL_TECHNICAL = "Vocational-technical";
	public static final String INCOMPLETE_HIGHER_EDUCATION = "Incomplete-higher";

	private static List<String> formEducationList = Arrays.asList(FULL_FORM, PART_FORM, DISTANT_FORM);
	private static List<String> kindEducationList = Arrays.asList(HIGHER, AVERAGE, SPECIALIZED_SECONDARY,
			VOCATIONAL_TECHNICAL, INCOMPLETE_HIGHER_EDUCATION);
	
	private String kindEducation;
	private String university;
	private String faculty;
	private String specialty;
	private String formEducation;
	private String educationDescription;
	private Date educationFrom;
	private Date educationTo;

	public Education() {

	}

	public Education(String kindEducation, String university, String faculty, String specialty, String formEducation,
			String educationDescription) {
		super();
		this.kindEducation = kindEducation;
		this.university = university;
		this.faculty = faculty;
		this.specialty = specialty;
		this.formEducation = formEducation;
		this.educationDescription = educationDescription;
	}

	public Education(String university, String faculty, String specialty, String educationDescription) {
		super();
		this.university = university;
		this.faculty = faculty;
		this.specialty = specialty;
		this.educationDescription = educationDescription;
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

	public static List<String> getFormEducationList() {
		return formEducationList;
	}

	public static List<String> getKindEducationList() {
		return kindEducationList;
	}

}
