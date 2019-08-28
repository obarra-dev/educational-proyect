package com.omm.mail.service;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilStringTemplate {
    public static String completeAndRender(final String path, final String templateName,
                                           final Map<String, Object> attributes){
        StringTemplateGroup stGroup = new StringTemplateGroup("keyGroup", path, DefaultTemplateLexer.class);
        StringTemplate emailBodyST = stGroup.getInstanceOf(templateName,attributes);
        return String.valueOf(emailBodyST);
    }

    public static String completeAndRender(final String templateName, final Map<String, Object> attributes){
        return completeAndRender(
                "C:\\Users\\obarra\\all-repos\\educational-proyect\\spring-boot-mail\\src\\main\\resources\\template",
                templateName, attributes);
    }
}
