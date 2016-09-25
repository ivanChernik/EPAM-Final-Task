package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * JavaBean for education of applicant.
 * 
 * @author Ivan Chernikau
 *
 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((educationDescription == null) ? 0 : educationDescription.hashCode());
		result = prime * result + ((educationFrom == null) ? 0 : educationFrom.hashCode());
		result = prime * result + ((educationTo == null) ? 0 : educationTo.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + ((formEducation == null) ? 0 : formEducation.hashCode());
		result = prime * result + ((kindEducation == null) ? 0 : kindEducation.hashCode());
		result = prime * result + ((specialty == null) ? 0 : specialty.hashCode());
		result = prime * result + ((university == null) ? 0 : university.hashCode());
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
		Education other = (Education) obj;
		if (educationDescription == null) {
			if (other.educationDescription != null)
				return false;
		} else if (!educationDescription.equals(other.educationDescription))
			return false;
		if (educationFrom == null) {
			if (other.educationFrom != null)
				return false;
		} else if (!educationFrom.equals(other.educationFrom))
			return false;
		if (educationTo == null) {
			if (other.educationTo != null)
				return false;
		} else if (!educationTo.equals(other.educationTo))
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (formEducation == null) {
			if (other.formEducation != null)
				return false;
		} else if (!formEducation.equals(other.formEducation))
			return false;
		if (kindEducation == null) {
			if (other.kindEducation != null)
				return false;
		} else if (!kindEducation.equals(other.kindEducation))
			return false;
		if (specialty == null) {
			if (other.specialty != null)
				return false;
		} else if (!specialty.equals(other.specialty))
			return false;
		if (university == null) {
			if (other.university != null)
				return false;
		} else if (!university.equals(other.university))
			return false;
		return true;
	}

	
}
