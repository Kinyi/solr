package cn.usth.iot.solr;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class Test {
	private static String baseURL = "http://122.0.67.162:8983/solr/collection1";
	private static HttpSolrServer solrServer = new HttpSolrServer(baseURL);

	public static void main(String[] args) throws Exception {
		SolrQuery params = new SolrQuery();
		params.set("q", "*:*");
		params.set("start", "0");
		params.set("rows", "34");
		QueryResponse response = solrServer.query(params);
		List<SolrVo> list = response.getBeans(SolrVo.class);
		for (SolrVo solrVo : list) {
			System.out.println(solrVo.getId()+"|"+solrVo.getName());
		}
	}

	private static void index() throws SolrServerException, IOException {
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		solrInputDocument.addField("id", "123");
		solrInputDocument.addField("name", "crxy");
		solrServer.add(solrInputDocument);
		solrServer.commit();
	}

	private static void query() throws SolrServerException {
		SolrQuery params = new SolrQuery();
		params.set("q", "*:*");
		QueryResponse response = solrServer.query(params);
		SolrDocumentList results = response.getResults();
		long numFound = results.getNumFound();
		System.out.println("总数:" + numFound);
		for (SolrDocument solrDocument : results) {
			Collection<String> fieldNames = solrDocument.getFieldNames();
			for (String field : fieldNames) {
				System.out.println(field + "=" + solrDocument.get(field));
			}
		}
	}
}