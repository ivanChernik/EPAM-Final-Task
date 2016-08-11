package by.epam.tc.hr_system.service.impl;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IResumeDAO;
import by.epam.tc.hr_system.dao.IVacancyDAO;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IResumeService;

public class ResumeServiceImpl implements IResumeService {

	private static final Logger log = Logger.getLogger(ResumeServiceImpl.class);

	@Override
	public void addResume(Resume resume, int userId) throws ServiceException {

		PreviousPosition prevPosition = resume.getPreviousWorkList().get(0);

		if (prevPosition.getPreviousPosition() == null || prevPosition.getPreviousPosition().isEmpty()) {
			log.error("Error addiction resume (prev work): position name");
			throw new ServiceException("Error addiction resume (prev work): position name");
		}

		if (prevPosition.getWorkDescription() == null || prevPosition.getWorkDescription().isEmpty()) {
			log.error("Error addiction resume (prev work): description");
			throw new ServiceException("Error addiction resume (prev work): description");
		}

		if (prevPosition.getWorkFrom() == null) {
			log.error("Error addiction resume (prev work): date from");
			throw new ServiceException("Error addiction resume (prev work): date from");
		}

		if (prevPosition.getWorkTo() == null) {
			log.error("Error addiction resume (prev work): date to");
			throw new ServiceException("Error addiction resume (prev work): date to");
		}

		if (prevPosition.getWorkTo().before(prevPosition.getWorkFrom())) {
			log.error("Error addiction resume (prev work): date to < date from");
			throw new ServiceException("Error addiction resume (prev work): date to < date from");
		}

		Education education = resume.getEducationList().get(0);

		if (education.getEducationDescription() == null || education.getEducationDescription().isEmpty()) {
			log.error("Error addiction resume (education): description");
			throw new ServiceException("Error addiction resume (education): description ");
		}

		if (education.getUniversity() == null || education.getUniversity().isEmpty()) {
			log.error("Error addiction resume (education): university ");
			throw new ServiceException("Error addiction resume (education): university ");
		}

		if (education.getFaculty() == null || education.getFaculty().isEmpty()) {
			log.error("Error addiction resume (education): faculty ");
			throw new ServiceException("Error addiction resume (education): faculty ");
		}

		if (education.getSpecialty() == null || education.getSpecialty().isEmpty()) {
			log.error("Error addiction resume (education): specialty ");
			throw new ServiceException("Error addiction resume (education): specialty ");
		}

		if (education.getEducationFrom() == null) {
			log.error("Error addiction resume (education): date from ");
			throw new ServiceException("Error addiction resume (education): date from ");
		}

		if (education.getEducationTo() == null) {
			log.error("Error addiction resume (education): date to ");
			throw new ServiceException("Error addiction resume (education): date to ");
		}

		if (education.getEducationFrom().after(education.getEducationTo())) {
			log.error("Error addiction resume (education): date to > date from");
			throw new ServiceException("Error addiction resume (education): date to > date from");
		}

		// from education

		if (education.getFormEducation() == null || education.getFormEducation().isEmpty()) {
			log.error("Error addiction resume (education): education form");
			throw new ServiceException("Error addiction resume (education): education form ");
		}

//		if (!education.getFormEducation().equals(Education.FULL_TIME)
//				|| !education.getFormEducation().equals(Education.PART_TIME)
//				|| !education.getFormEducation().equals(Education.DISTANT)) {
//			log.error("Error addiction resume (education): education form");
//			throw new ServiceException("Error addiction resume (education): education form ");
//		}

		// kinf of education

		if (education.getKindEducation() == null || education.getKindEducation().isEmpty()) {
			log.error("Error addiction resume (education): kind education");
			throw new ServiceException("Error addiction resume (education): kind education");
		}

//		if (!education.equals(Education.HIGHER) || !education.equals(Education.INCOMPLETE_HIGHER_EDUCATION)
//				|| !education.equals(Education.SPECIALIZED_SECONDARY) || !education.equals(Education.AVERAGE)
//				|| !education.equals(Education.VOCATIONAL_TECHNICAL)) {
//			log.error("Error addiction resume (education):kind education ");
//			throw new ServiceException("Error addiction resume (education): kind education");
//		}

		// other information

		if (resume.getSkill() == null || resume.getSkill().isEmpty()) {
			log.error("Error addiction resume (resume info): skill ");
			throw new ServiceException("Error addiction resume (resume info): skill ");
		}
		if (resume.getPosition() == null || resume.getPosition().isEmpty()) {
			log.error("Error addiction resume (resume info): position ");
			throw new ServiceException("Error addiction resume (resume info): position ");
		}
		if (resume.getProfInformation() == null || resume.getProfInformation().isEmpty()) {
			log.error("Error addiction resume (resume info): prof information ");
			throw new ServiceException("Error addiction resume (resume info): prof information ");
		}

		if (userId < 0) {
			log.error("Error addiction resume: userId ");
			throw new ServiceException("Error addiction resume: userId ");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			resumeDAO.addResume(resume, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public int getCountResumes() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		int countResumes = 0;
		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			countResumes = resumeDAO.getCountResumes();
		} catch (DAOException e) {
			 throw new ServiceException(e);
		}
		return countResumes;
	}

	@Override
	public Resume getApplicantResume(String idResumeString) throws ServiceException {
		
		if(idResumeString == null || idResumeString.isEmpty()){
			log.error("Error getting resume: idResume ");
			throw new ServiceException("Error getting resume: idResume ");
		}
		
		int idResume = Integer.parseInt(idResumeString);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		Resume resume = null;
		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			resume = resumeDAO.getApplicantResume(idResume);
		} catch (DAOException e) {
			 throw new ServiceException(e);
		}
		return resume;
	}

}
