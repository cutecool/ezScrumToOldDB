package ntut.csie.ezScrum.web.action.backlog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ntut.csie.ezScrum.issue.core.IIssue;
import ntut.csie.ezScrum.pic.core.IUserSession;
import ntut.csie.ezScrum.web.action.PermissionAction;
import ntut.csie.ezScrum.web.dataObject.StoryInformation;
import ntut.csie.ezScrum.web.helper.ProductBacklogHelper;
import ntut.csie.ezScrum.web.helper.ReleasePlanHelper;
import ntut.csie.ezScrum.web.support.SessionManager;
import ntut.csie.jcis.resource.core.IProject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AjaxAddNewStoryAction extends PermissionAction {
	private static Log log = LogFactory.getLog(AjaxAddNewStoryAction.class);
	
	@Override
	public boolean isValidAction() {
		return super.getScrumRole().getAccessProductBacklog();
	}

	@Override
	public boolean isXML() {
		// html
		return false;
	}

	@Override
	public StringBuilder getResponse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		long time1 = System.currentTimeMillis();
		log.info("Add New Story in AjaxAddNewStoryAction.");
		IProject project = (IProject) SessionManager.getProject(request);
		IUserSession session = (IUserSession) request.getSession().getAttribute("UserSession");
		
		String name = request.getParameter("Name");
		String importance = request.getParameter("Importance");
		String estimation = request.getParameter("Estimation");
		String value = request.getParameter("Value");
		String howToDemo = request.getParameter("HowToDemo");
		String notes = request.getParameter("Notes");
		String description = "";
		String sprintID = request.getParameter("sprintId");
		String tagIDs = request.getParameter("TagIDs");
		String releaseID = "";
		
		StoryInformation storyInformation = new StoryInformation(name, importance, estimation, value, howToDemo, notes, description, sprintID, releaseID, tagIDs);
		releaseID = new ReleasePlanHelper(project).getReleaseID(storyInformation.getSprintID());
		if (!releaseID.equals("0")){
			storyInformation.setReleaseID(releaseID);
		}
		
		ProductBacklogHelper productBacklogHelper = new ProductBacklogHelper(session, project);
		IIssue issue = productBacklogHelper.addNewStory(storyInformation);
		StringBuilder result = productBacklogHelper.translateStoryToJson(issue);
		long time2 = System.currentTimeMillis();
		System.out.println("AjaxAddNewStoryAction:" + (time2 - time1));
		return result;
	}
}
