package by.epam.tc.hr_system.command.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.domain.ApplicantContactInfo;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.ResumeParamater;

public class CreateResumeCommand implements ICommand {

	private static final String FILE = "photo";
	private static final String PICTURE_UPLOAD_PATH = "/img/";
	private static final String IMAGE_MIME_TYPE = "image/";
	private static final String PERSON = "person";

	private static final Logger log = Logger.getLogger(CreateResumeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			HttpSession session = request.getSession(true);

			Person person = (Person) session.getAttribute(PERSON);

			if (person == null) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}

			Resume resume = new Resume();

			resume.setPosition(request.getParameter(ResumeParamater.POSITION));
			resume.setProfInformation(request.getParameter(ResumeParamater.PROF_INFIRMATION));
			resume.setSkill(request.getParameter(ResumeParamater.SKILL));

			Education education = new Education();

			education.setKindEducation(request.getParameter(ResumeParamater.KIND_EDUCATION));
			education.setUniversity(request.getParameter(ResumeParamater.UNIVERSITY));
			education.setFaculty(request.getParameter(ResumeParamater.FACULTY));
			education.setSpecialty(request.getParameter(ResumeParamater.SPECIALTY));
			education.setFormEducation(request.getParameter(ResumeParamater.FORM_EDUCATION));
			education.setEducationFrom(Date.valueOf(request.getParameter(ResumeParamater.EDUCATION_FROM)));
			education.setEducationTo(Date.valueOf(request.getParameter(ResumeParamater.EDUCATION_TO)));
			education.setEducationDescription(request.getParameter(ResumeParamater.EDUCATION_DESCRIPTION));

			resume.addEducation(education);

			PreviousPosition prevPosition = new PreviousPosition();
			prevPosition.setPreviousPosition(request.getParameter(ResumeParamater.PREVIOS_POSITION));
			prevPosition.setWorkFrom(Date.valueOf(request.getParameter(ResumeParamater.WORK_FROM)));
			prevPosition.setWorkTo(Date.valueOf(request.getParameter(ResumeParamater.WORK_TO)));
			prevPosition.setWorkDescription(request.getParameter(ResumeParamater.WORK_DESCRIPTION));

			resume.addPreviousWork(prevPosition);

			ApplicantContactInfo contactInfo = new ApplicantContactInfo();

			contactInfo.setPhone(request.getParameter(ResumeParamater.PHONE));
			contactInfo.setEmail(request.getParameter(ResumeParamater.EMAIL));
			contactInfo.setAddress(request.getParameter(ResumeParamater.ADDRESS));

			contactInfo.setLinkGooglePlus(request.getParameter(ResumeParamater.LINK_GOOGLE_PLUS));
			contactInfo.setLinkLinkedIn(request.getParameter(ResumeParamater.LINK_LINKEDIN));
			contactInfo.setLinkTwitter(request.getParameter(ResumeParamater.LINK_TWITTER));
			contactInfo.setLinkFacebook(request.getParameter(ResumeParamater.LINK_FACEBOOK));

			resume.setContactInfo(contactInfo);

			Part filePart = request.getPart(FILE);
			String filename = filePart.getSubmittedFileName();
			if (!filename.isEmpty()) {
				String mimeType = request.getServletContext().getMimeType(filename);
				if (mimeType.startsWith(IMAGE_MIME_TYPE)) {
					File uploads = new File(request.getServletContext()
							.getRealPath("")/* + PICTURE_UPLOAD_PATH */);
					File file = new File(uploads, filename);
					try (InputStream input = filePart.getInputStream()) {
						Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}

					resume.setPathImage(/* PICTURE_UPLOAD_PATH + */filename);

					ServiceFactory serviceFactory = ServiceFactory.getInstance();

					try {
						IResumeService resumeService = serviceFactory.getResumeService();
						resumeService.addResume(resume, 9);
						request.getRequestDispatcher(PageName.INDEX_APPLICANT_PAGE).forward(request, response);
						return;
					} catch (ServiceException e) {
						request.getRequestDispatcher(PageName.CREATE_VACANCY_PAGE).forward(request, response);
						return;
					}
					
					// product.setImgPath(PICTURE_UPLOAD_PATH + filename);
					// ProductService productService =
					// ProductServiceImpl.getInstance();
					// boolean success = productService.addProduct(product);
					// if (!success) {
					// request.setAttribute(MessageManager.MESSAGE,
					// MessageManager.ADDING_ERROR);
					// request.getRequestDispatcher(PageName.EDIT_PRODUCTS).forward(request,
					// response);
					// return;
					// }
				} else {
					// request.setAttribute(MessageManager.MESSAGE,
					// MessageManager.NOT_JPG_IMAGE);
				}
			} else {
				// request.setAttribute(MessageManager.MESSAGE,
				// MessageManager.ADDING_ERROR);
				// request.getRequestDispatcher(PageName.EDIT_PRODUCTS).forward(request,
				// response);
				return;
			}
			// response.sendRedirect(PageName.EDIT_PRODUCTS);
			// } catch (ServiceException e) {
			// LOGGER.error("Error add product", e);
			// request.setAttribute(MessageManager.MESSAGE,
			// MessageManager.DATABASE_ERROR);
			// request.getRequestDispatcher(PageName.EDIT_PRODUCTS).forward(request,
			// response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
	}

}
