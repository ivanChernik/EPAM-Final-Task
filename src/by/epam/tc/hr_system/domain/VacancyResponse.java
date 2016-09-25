package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * JavaBean for response to Vacancy
 * 
 * @author Ivan Chernikau
 *
 */
public class VacancyResponse implements Serializable{

	public static final String VIEWED_STATUS = "viewed";
	public static final String NOT_VIEWED_STATUS = "not-viewed";
	public static final String REJECTION_STATUS = "rejection";
	public static final String INVITATION_FOR_INTERVIEW_STATUS = "invitation-for-interview";
	
	private static List<String> statusList = Arrays.asList(VIEWED_STATUS,NOT_VIEWED_STATUS,REJECTION_STATUS,INVITATION_FOR_INTERVIEW_STATUS);
	private int id;
	private Person person = new Person();
	private Resume resume = new Resume();
	private Vacancy vacancy = new Vacancy();
	private String status;
	private Date date;
	
	public VacancyResponse(){
		
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public Resume getResume() {
		return resume;
	}


	public void setResume(Resume resume) {
		this.resume = resume;
	}


	public Vacancy getVacancy() {
		return vacancy;
	}


	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public static List<String> getStatusList() {
		return statusList;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((resume == null) ? 0 : resume.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((vacancy == null) ? 0 : vacancy.hashCode());
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
		VacancyResponse other = (VacancyResponse) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (resume == null) {
			if (other.resume != null)
				return false;
		} else if (!resume.equals(other.resume))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (vacancy == null) {
			if (other.vacancy != null)
				return false;
		} else if (!vacancy.equals(other.vacancy))
			return false;
		return true;
	}

}
