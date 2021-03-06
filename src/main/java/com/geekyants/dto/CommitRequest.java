package com.geekyants.dto;
/**
 * @author @dibyapp, Name : Dibyaprakash, Email : dibyaprakash@geekyants.com
 * @Project : timesheet-helper
 */
import java.util.List;

public class CommitRequest {
	
	private List<Commit> commits;

	public List<Commit> getCommits() {
		return commits;
	}

	public void setCommits(List<Commit> commits) {
		this.commits = commits;
	}

	@Override
	public String toString() {
		return "CommitRequest [commits=" + commits + "]";
	}


}
