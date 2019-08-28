package com.omm.mail.model;

import java.util.List;
import java.util.Map;

public class NotificationDTO {

    //@NotEmpty(message = "to cannot be empty")
    private String[] to;
    //@NotEmpty(message = "to cannot be empty")
    private String templateKey;

    private Map<String, Object> templateParameters;

    private List<FileDocumentDTO> files;

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getTemplateKey() {
        return templateKey;
    }

    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
    }

    public Map<String, Object> getTemplateParameters() {
        return templateParameters;
    }

    public void setTemplateParameters(Map<String, Object> templateParameters) {
        this.templateParameters = templateParameters;
    }

    public List<FileDocumentDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDocumentDTO> files) {
        this.files = files;
    }
}
