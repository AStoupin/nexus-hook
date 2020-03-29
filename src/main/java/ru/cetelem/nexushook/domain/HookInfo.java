package ru.cetelem.nexushook.domain;

public class HookInfo {
	private String componentName;
	private String jobName;
	
	public HookInfo(String componentName, String jobName) {
		super();
		this.componentName = componentName;
		this.jobName = jobName;
	}
	
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public String toString() {
		return "HookInfo [componentName=" + componentName + ", jobName=" + jobName + "]";
	}
}
