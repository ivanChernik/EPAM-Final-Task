package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resume implements Serializable{
	
	private int id;
	private Person person = new Person();;
	private String pathImage;
	private String position;
	private String profInformation;
	private String skill;
	private ApplicantContactInfo contactInfo;
	private List<PreviousPosition> previousWorkList = new ArrayList<PreviousPosition>();
	private List<Education> educationList = new ArrayList<Education>();
	
	public Resume(){

	}
	
	public void addPreviousWork(PreviousPosition previousWork){
		previousWorkList.add(previousWork);
	}
	
	public void addEducation(Education education){
		educationList.add(education);
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String postion) {
		this.position = postion;
	}

	public String getProfInformation() {
		return profInformation;
	}

	public void setProfInformation(String profInformation) {
		this.profInformation = profInformation;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public List<PreviousPosition> getPreviousWorkList() {
		return previousWorkList;
	}

	public void setPreviousWorkList(List<PreviousPosition> previousWorkList) {
		this.previousWorkList = previousWorkList;
	}

	public List<Education> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<Education> previousEducationList) {
		this.educationList = previousEducationList;
	}	
	
	public ApplicantContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ApplicantContactInfo contactInfo) {
		this.contactInfo = contactInfo;
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

}
