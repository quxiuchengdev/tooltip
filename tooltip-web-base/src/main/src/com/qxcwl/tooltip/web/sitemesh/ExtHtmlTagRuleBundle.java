package com.qxcwl.tooltip.web.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * @Author 曲修成
 * @ClassName ExtHtmlTagRuleBundle
 * @Description
 * @Date 2016-11-25 14:15:00
 */
public class ExtHtmlTagRuleBundle implements TagRuleBundle {

	@Override
	public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
		defaultState.addRule("footScriptTag", new ExportTagToContentRule(siteMeshContext,contentProperty.getChild("footScriptTag"),false));
	}

	@Override
	public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

	}
}
