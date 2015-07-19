package cn.usth.iot.solr;

import org.apache.solr.client.solrj.beans.Field;

public class SolrVo {

	@Field
	String id;
	
	@Field
	String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
