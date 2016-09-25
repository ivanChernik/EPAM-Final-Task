package by.epam.tc.hr_system.domain;

import java.io.Serializable;

/**
 * Javabean for contact information of applicant.
 * 
 * @author Ivan Chernikau
 *
 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((linkFacebook == null) ? 0 : linkFacebook.hashCode());
		result = prime * result + ((linkGooglePlus == null) ? 0 : linkGooglePlus.hashCode());
		result = prime * result + ((linkLinkedIn == null) ? 0 : linkLinkedIn.hashCode());
		result = prime * result + ((linkTwitter == null) ? 0 : linkTwitter.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		ApplicantContactInfo other = (ApplicantContactInfo) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (linkFacebook == null) {
			if (other.linkFacebook != null)
				return false;
		} else if (!linkFacebook.equals(other.linkFacebook))
			return false;
		if (linkGooglePlus == null) {
			if (other.linkGooglePlus != null)
				return false;
		} else if (!linkGooglePlus.equals(other.linkGooglePlus))
			return false;
		if (linkLinkedIn == null) {
			if (other.linkLinkedIn != null)
				return false;
		} else if (!linkLinkedIn.equals(other.linkLinkedIn))
			return false;
		if (linkTwitter == null) {
			if (other.linkTwitter != null)
				return false;
		} else if (!linkTwitter.equals(other.linkTwitter))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}
