package tests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import api.APIBase;

public class APITest extends APIBase{
	
	Set<Object> convertJSONArrayToSet(JSONArray input) throws JSONException {
	    Set<Object> retVal = new HashSet<>();
	    for (int i = 0; i < input.length(); i++) {
	        retVal.add(input.get(i));
	    }
	    return retVal;
	}
	
	private boolean anyMatch(JSONArray first, JSONArray second) throws JSONException {
        Set<Object> firstAsSet = convertJSONArrayToSet(first);
        Set<Object> secondIdsAsSet = convertJSONArrayToSet(second);
        // use stream API anyMatch
        return firstAsSet.equals(secondIdsAsSet);
    }
	
	@Test
	public void apiTest() throws Exception {
		HashMap<String, String> mp = new HashMap<String,String>();
		mp.put("searchVal", "gateway%20west");
		mp.put("returnGeom", "Y");
		mp.put("getAddrDetails", "Y");
		mp.put("pageNum", "1");
		JSONObject textSearchResult = getRequest("https://developers.onemap.sg/commonapi/search", mp);
		System.out.println(textSearchResult.getJSONArray("results"));
		
		mp.put("searchVal", "189720");
		mp.put("returnGeom", "Y");
		mp.put("getAddrDetails", "Y");
		mp.put("pageNum", "1");
		JSONObject postalSearchResult = getRequest("https://developers.onemap.sg/commonapi/search", mp);
		System.out.println(postalSearchResult.getJSONArray("results"));
		Assert.assertTrue(anyMatch(textSearchResult.getJSONArray("results"), postalSearchResult.getJSONArray("results")));
		
	}

}
