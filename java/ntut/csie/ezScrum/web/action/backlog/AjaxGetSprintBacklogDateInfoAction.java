package ntut.csie.ezScrum.web.action.backlog;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ntut.csie.ezScrum.pic.core.IUserSession;
import ntut.csie.ezScrum.web.helper.SprintBacklogHelper;
import ntut.csie.ezScrum.web.support.SessionManager;
import ntut.csie.jcis.resource.core.IProject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AjaxGetSprintBacklogDateInfoAction extends Action {
	private static Log log = LogFactory.getLog(AjaxGetSprintBacklogDateInfoAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		long time1 = System.currentTimeMillis();
		log.info("Get Sprint Backlog Date.");
		
		IProject project = (IProject) SessionManager.getProject(request);
		IUserSession userSession = (IUserSession) request.getSession().getAttribute("UserSession");
		
		String sprintID = request.getParameter("sprintID");

		String result = (new SprintBacklogHelper(project, userSession, sprintID)).getAjaxGetSprintBacklogDateInfo();
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().write(result);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long time2 = System.currentTimeMillis();
		System.out.println("AjaxGetSprintBacklogDateInfoAction:" + (time2 - time1));
		return null;
	}
}