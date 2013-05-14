package edu.fyp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.Application;
import edu.fyp.bean.Form;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.manager.ApplicationMonitorManager;
import edu.fyp.manager.FormManager;

@Controller
public class PathController {

	@Autowired
	private FormManager formManager;

	@Autowired
	private ApplicationMonitorManager appMonManager;

	@Autowired
	private ApplicationManager appManager;

	@RequestMapping("/path")
	public ModelAndView path() {
		ModelAndView mav = new ModelAndView("path/pathbuilder");
		return mav;
	}

	@RequestMapping(value = "/path", params = { "formKey" })
	public ModelAndView pathWithFormKey(
			@RequestParam(value = "formKey", required = true) String formKey) {
		ModelAndView mav = new ModelAndView("path/pathbuilder");
		Form form = formManager.getFormByFormKey(formKey);
		if (form.getPath() == null || "".equals(form.getPath().getValue()))
			mav.addObject("path", "''");
		else
			mav.addObject("path", form.getPath().getValue());
		mav.addObject("formKey", formKey);
		return mav;
	}

	@RequestMapping(value = "/pathReadOnly", params = { "appKey" })
	public ModelAndView pathReadOnly(
			@RequestParam(value = "appKey", required = true) String appKey) {
		ModelAndView mav = new ModelAndView("path/pathviewer");
		String json = appMonManager.getApplicationPath(KeyFactory
				.stringToKey(appKey));
		String pathJson;
		Application app = appManager.getApplication(KeyFactory
				.stringToKey(appKey));
		Form form = formManager.getFormByIDVersion(app.getFormID(), app.getVersion());
		if (form.getPath() == null || "".equals(form.getPath().getValue()))
			mav.addObject("path", "''");
		else
			mav.addObject("path", form.getPath().getValue());
		mav.addObject("appKey", appKey);
		if (json == null || "".equals(json))
			mav.addObject("appJson", "''");
		else
			mav.addObject("appJson", json);

		return mav;
	}

	@RequestMapping("/searchUserPanel")
	public ModelAndView searchUserPanel() {
		ModelAndView mav = new ModelAndView("path/searchUser");
		return mav;
	}

}
