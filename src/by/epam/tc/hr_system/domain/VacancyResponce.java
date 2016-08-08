package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.sql.Date;

public class VacancyResponce implements Serializable{

	public static final String VIEWED_STATUS = "viewed";
	public static final String NOT_VIEWED_STATUS = "not-viewed";
	public static final String REJECTION_STATUS = "rejection";
	public static final String INVITATION_FOR_INTERVIEW_STATUS = "invitation-for-interview";
	
	private int idResume;
	private int idVacancy;
	private String сompanyName;
	private String status;
	private Date date;
	
	public VacancyResponce(){
		
	}
	

	public VacancyResponce(int idResume, int idVacancy, String status, Date date) {
		super();
		this.idResume = idResume;
		this.idVacancy = idVacancy;
		this.status = status;
		this.date = date;
	}



	public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	public int getIdVacancy() {
		return idVacancy;
	}

	public void setIdVacancy(int idVacancy) {
		this.idVacancy = idVacancy;
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


	public String getCompanyName() {
		return сompanyName;
	}


	public void setCompanyName(String companyName) {
		сompanyName = companyName;
	}
	
}
