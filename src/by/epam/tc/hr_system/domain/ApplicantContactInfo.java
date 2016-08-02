package by.epam.tc.hr_system.domain;

import java.io.Serializable;

public class ApplicantContactInfo implements Serializable{
	private String linkGooglePlus;
	private String linkLinkedIn;
	private String linkTwitter;
	private String linkFacebook;
	
	private String phone;
	private String email;
	private String address;
	
	public ApplicantContactInfo(){
		
	}
	
	public String getLinkGooglePlus() {
		return linkGooglePlus;
	}

	public void setLinkGooglePlus(String linkGooglePlus) {
		this.linkGooglePlus = linkGooglePlus;
	}

	public String getLinkLinkedIn() {
		return linkLinkedIn;
	}

	public void setLinkLinkedIn(String linkLinkedIn) {
		this.linkLinkedIn = linkLinkedIn;
	}

	public String getLinkTwitter() {
		return linkTwitter;
	}

	public void setLinkTwitter(String linkTwitter) {
		this.linkTwitter = linkTwitter;
	}

	public String getLinkFacebook() {
		return linkFacebook;
	}

	public void setLinkFacebook(String linkFacebook) {
		this.linkFacebook = linkFacebook;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
