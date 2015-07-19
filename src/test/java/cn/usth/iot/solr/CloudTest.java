package cn.usth.iot.solr;

import java.util.Collection;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class CloudTest {

	@Test
	public void Query() throws Exception {
		String zkHost = "192.168.1.170:2181";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		solrServer.setDefaultCollection("collection1");
		
		SolrQuery solrQuery = new SolrQuery();
		//solrQuery.set("q", "*:*");
		solrQuery.setQuery("*:*");
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList results = response.getResults();
		for (SolrDocument solrDocument : results) {
			Collection<String> fieldNames = solrDocument.getFieldNames();
			for (String field : fieldNames) {
				System.out.println(field + "=" +solrDocument.get(fieldNames));
			}
		}
	}
}
