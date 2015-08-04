package ntut.csie.ezScrum.test;

import java.util.ArrayList;
import java.util.List;

import ntut.csie.ezScrum.issue.core.IIssue;
import ntut.csie.ezScrum.issue.core.IIssueHistory;
import ntut.csie.ezScrum.test.CreateData.ezScrumInfoConfig;
import ntut.csie.ezScrum.web.helper.AccountHelper;
import ntut.csie.ezScrum.web.helper.ProductBacklogHelper;
import ntut.csie.ezScrum.web.helper.ProjectHelper;
import ntut.csie.ezScrum.web.helper.SprintBacklogHelper;
import ntut.csie.ezScrum.web.logic.ProjectLogic;
import ntut.csie.jcis.account.core.AccountFactory;
import ntut.csie.jcis.account.core.IAccount;
import ntut.csie.jcis.resource.core.IProject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccessDataTest {
	private ezScrumInfoConfig config = new ezScrumInfoConfig();
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testAccessProjectList() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		IAccount account = AccountFactory.getManager().getAccount("admin");
		ProjectHelper projectHelper = new ProjectHelper();
		projectHelper.getProjectListXML(account);
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			projectHelper.getProjectListXML(account);
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access ProjectList avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessProductBacklog() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		IProject project = (new ProjectLogic()).getAllProjects().get(0);
		ProductBacklogHelper pbHelper = new ProductBacklogHelper(config.getUserSession(), project);
		pbHelper.getShowProductBacklogResponseText("").toString();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			pbHelper.getShowProductBacklogResponseText("");
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access ProductBacklog avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessTag() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		IProject project = (new ProjectLogic()).getAllProjects().get(0);
		ProductBacklogHelper pbHelper = new ProductBacklogHelper(config.getUserSession(), project);
		pbHelper.getTagListResponseText().toString();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			pbHelper.getShowProductBacklogResponseText("");
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Tag avg time： " + averageTime(timeStamps));
	}
	
//	@Test
//	public void testAccessRelease() {
//		
//	}
//	
//	@Test
//	public void testAccessSprint() {
//		
//	}
	
	@Test
	public void testAccessSprintBacklog() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		IProject project = (new ProjectLogic()).getAllProjects().get(0);
		SprintBacklogHelper sbHelper = new SprintBacklogHelper(project, config.getUserSession());
		sbHelper.getSprintBacklogListInfoText();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			sbHelper.getSprintBacklogListInfoText();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access SprintBacklog avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessExistingStory() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		IProject project = (new ProjectLogic()).getAllProjects().get(0);
		SprintBacklogHelper sbHelper = new SprintBacklogHelper(project, config.getUserSession(), "1");
		sbHelper.getExistedStories("-1");
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			sbHelper.getExistedStories("-1");
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Existing Story avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessExistingTask() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		IProject project = (new ProjectLogic()).getAllProjects().get(0);
		ntut.csie.ezScrum.web.control.ProductBacklogHelper pbHelper = new ntut.csie.ezScrum.web.control.ProductBacklogHelper(project, config.getUserSession());
		pbHelper.getAddableTasks();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			pbHelper.getAddableTasks();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Existing Task avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessStoryHistory() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		IProject project = (new ProjectLogic()).getAllProjects().get(0);
		ProductBacklogHelper pbHelper = new ProductBacklogHelper(config.getUserSession(), project);
		IIssue story = pbHelper.getIssue(202);
		story.getIssueHistories();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			story.getIssueHistories();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Story History avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessTaskHistory() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		IProject project = (new ProjectLogic()).getAllProjects().get(0);
		ProductBacklogHelper pbHelper = new ProductBacklogHelper(config.getUserSession(), project);
		IIssue task = pbHelper.getIssue(102);
		task.getIssueHistories();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			task.getIssueHistories();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Task History avg time： " + averageTime(timeStamps));
	}
	
	@Test
	public void testAccessAccount() {
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		AccountHelper aHelper = new AccountHelper();
		aHelper.getAccountListXML();
		
		for (int i = 0; i < 10; i++) {
			long time1 = System.currentTimeMillis();
			aHelper.getAccountListXML();
			long time2 = System.currentTimeMillis();
			timeStamps.add(time2 - time1);
		}
		
		System.out.println("Access Account avg time： " + averageTime(timeStamps));
	}
	
	private double averageTime(ArrayList<Long> timeStamps) {
		double avgTime;
		long total = 0;
		for (Long timeStamp : timeStamps) {
			total += timeStamp;
		}
		avgTime = (double)total / timeStamps.size();
		return avgTime;
	}
}
