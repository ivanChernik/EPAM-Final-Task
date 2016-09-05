package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class VacancyResponce implements Serializable{

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
	
	public VacancyResponce(){
		
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

}
