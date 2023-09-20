package diginamic.lightRh.dtos;

import java.util.Date;
import java.util.List;

import diginamic.lightRh.entities.Employee;
import diginamic.lightRh.enums.AbsenceStatusEnum;
import diginamic.lightRh.enums.AbsenceTypeEnum;

public class AbsenceSearchFilter {
    Date searchAfter;
    Date searchBefore;
    List<AbsenceStatusEnum> includeSearchStatus;
    List<AbsenceStatusEnum> excludeSearchStatus;
    List<AbsenceTypeEnum> includeSearchType;
    List<AbsenceTypeEnum> excludeSearchType;
    boolean mustHaveMotif;
    String motifContainsString;
    String labelContainsString;
    List<Employee> includeSearchEmployee;
    List<Employee> excludeSearchEmployee;
    public Date getSearchAfter() {
        return searchAfter;
    }
    public void setSearchAfter(Date searchAfter) {
        this.searchAfter = searchAfter;
    }
    public Date getSearchBefore() {
        return searchBefore;
    }
    public void setSearchBefore(Date searchBefore) {
        this.searchBefore = searchBefore;
    }
    public List<AbsenceStatusEnum> getIncludeSearchStatus() {
        return includeSearchStatus;
    }
    public void setIncludeSearchStatus(List<AbsenceStatusEnum> includeSearchStatus) {
        this.includeSearchStatus = includeSearchStatus;
    }
    public List<AbsenceStatusEnum> getExcludeSearchStatus() {
        return excludeSearchStatus;
    }
    public void setExcludeSearchStatus(List<AbsenceStatusEnum> excludeSearchStatus) {
        this.excludeSearchStatus = excludeSearchStatus;
    }
    public List<AbsenceTypeEnum> getIncludeSearchType() {
        return includeSearchType;
    }
    public void setIncludeSearchType(List<AbsenceTypeEnum> includeSearchType) {
        this.includeSearchType = includeSearchType;
    }
    public List<AbsenceTypeEnum> getExcludeSearchType() {
        return excludeSearchType;
    }
    public void setExcludeSearchType(List<AbsenceTypeEnum> excludeSearchType) {
        this.excludeSearchType = excludeSearchType;
    }
    public boolean isMustHaveMotif() {
        return mustHaveMotif;
    }
    public void setMustHaveMotif(boolean mustHaveMotif) {
        this.mustHaveMotif = mustHaveMotif;
    }
    public String getMotifContainsString() {
        return motifContainsString;
    }
    public void setMotifContainsString(String motifContainsString) {
        this.motifContainsString = motifContainsString;
    }
    public String getLabelContainsString() {
        return labelContainsString;
    }
    public void setLabelContainsString(String labelContainsString) {
        this.labelContainsString = labelContainsString;
    }
    public List<Employee> getIncludeSearchEmployee() {
        return includeSearchEmployee;
    }
    public void setIncludeSearchEmployee(List<Employee> includeSearchEmployee) {
        this.includeSearchEmployee = includeSearchEmployee;
    }
    public List<Employee> getExcludeSearchEmployee() {
        return excludeSearchEmployee;
    }
    public void setExcludeSearchEmployee(List<Employee> excludeSearchEmployee) {
        this.excludeSearchEmployee = excludeSearchEmployee;
    }
   
}
