package com.shytong.core.generate;

import java.util.List;

/**
 *
 * @author sytong
 * @Package com.shytong.core.generate
 * @Description:
 * @date 2018-01-0317:17
 */
public class Do {

    private String id;
    private String moduleId;
    private String table;
    private String packageName;
    private String name;
    private String description;
    private String author;
    private String date;
    private String ruleType;
    private String rule;

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    private List<DoField> doFields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DoField> getDoFields() {
        return doFields;
    }

    public void setDoFields(List<DoField> doFields) {
        this.doFields = doFields;
    }
}
