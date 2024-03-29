package ntut.csie.ezScrum.web.action.backlog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ntut.csie.ezScrum.pic.core.IUserSession;
import ntut.csie.ezScrum.web.action.PermissionAction;
import ntut.csie.ezScrum.web.helper.ProjectHelper;
import ntut.csie.ezScrum.web.support.SessionManager;
import ntut.csie.jcis.resource.core.IProject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AjaxGetHandlerListAction extends PermissionAction {
	private static Log log = LogFactory.getLog(AjaxGetHandlerListAction.class);

	@Override
	public boolean isValidAction() {
		return true;	// 不知道怎麼分類，SprintBacklog、TaskBoard都會使用到
	}

	@Override
	public boolean isXML() {
		// XML
		return true;
	}

	@Override
	public StringBuilder getResponse(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response) {
		long time1 = System.currentTimeMillis();
		log.info("Get Handler List in the Summary Page.");

		// get project from session or DB
		IProject project = (IProject) SessionManager.getProject(request);
		IUserSession userSession = (IUserSession) request.getSession().getAttribute("UserSession");
		List<String> actors = (new ProjectHelper()).getProjectScrumWorkerList(userSession, project);

		StringBuilder result = new StringBuilder();
		result.append("<Handlers><Result>success</Result>");

		for (int i = 1; i < actors.size(); i++) {
			result.append("<Handler>")
				  .append("<Name>").append(actors.get(i)).append("</Name>")
				  .append("</Handler>");
		}
		result.append("</Handlers>");
		long time2 = System.currentTimeMillis();
		System.out.println("AjaxGetHandlerListAction:" + (time2 - time1));
		return result;
	}
}
