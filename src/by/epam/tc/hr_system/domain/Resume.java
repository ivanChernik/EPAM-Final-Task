package by.epam.tc.hr_system.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean for resume of applicant. 
 * 
 * @author Ivan Chernikau
 *
 */

public class Resume implements Serializable{
	
	private int id;
	private Person person = new Person();;
	private String pathImage;
	private String position;
	private String profInformation;
	private String skill;
	private ApplicantContactInfo contactInfo = new ApplicantContactInfo();
	private List<PreviousPosition> previousWorkList = new ArrayList<PreviousPosition>();
	private List<Education> educationList = new ArrayList<Education>();
	
	public Resume(){

	}
	
	
	
	public Resume(String position, String profInformation, String skill) {
		super();
		this.position = position;
		this.profInformation = profInformation;
		this.skill = skill;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactInfo == null) ? 0 : contactInfo.hashCode());
		result = prime * result + ((educationList == null) ? 0 : educationList.hashCode());
		result = prime * result + id;
		result = prime * result + ((pathImage == null) ? 0 : pathImage.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((previousWorkList == null) ? 0 : previousWorkList.hashCode());
		result = prime * result + ((profInformation == null) ? 0 : profInformation.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
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
		Resume other = (Resume) obj;
		if (contactInfo == null) {
			if (other.contactInfo != null)
				return false;
		} else if (!contactInfo.equals(other.contactInfo))
			return false;
		if (educationList == null) {
			if (other.educationList != null)
				return false;
		} else if (!educationList.equals(other.educationList))
			return false;
		if (id != other.id)
			return false;
		if (pathImage == null) {
			if (other.pathImage != null)
				return false;
		} else if (!pathImage.equals(other.pathImage))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (previousWorkList == null) {
			if (other.previousWorkList != null)
				return false;
		} else if (!previousWorkList.equals(other.previousWorkList))
			return false;
		if (profInformation == null) {
			if (other.profInformation != null)
				return false;
		} else if (!profInformation.equals(other.profInformation))
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		return true;
	}

	
}
