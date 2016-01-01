package com.earl.apply.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

public class HttpUserActionTest {

	@Test
	public void testApply() {
		String targetURL = "http://localhost:8080/apply/apply.action";

		PostMethod filePost = new PostMethod(targetURL);

		Part[] parts = { new StringPart("phoneNumber", "1832048943", "utf-8"),
				new StringPart("userName", "superGG", "utf-8") ,
				new StringPart("saying", "我想说点啥？？？", "utf-8") ,
				new StringPart("userClass", "软件1133", "utf-8") 
				
		};
		String sendHttpRequest = sendHttpRequest(filePost, parts);
		System.out.println(sendHttpRequest);
	}
	
	
	
	
	
	
	
	public String sendHttpRequest(PostMethod filePost, Part[] parts) {
		String string = null;
		try {
			HttpMethodParams params = filePost.getParams();
			params.setContentCharset("utf-8");
			filePost.setRequestEntity(new MultipartRequestEntity(parts,
					filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams()
			.setConnectionTimeout(50000);
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				
				
			} else {
			}
			InputStream in = filePost.getResponseBodyAsStream();
			byte[] readStream = readStream(in);
			string = new String(readStream);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			filePost.releaseConnection();
		}
		return string;
	}
	

	/** 
	 * 读取流 
	 *  
	 * @param inStream 
	 * @return 字节数组 
	 * @throws Exception 
	 */  
	private byte[] readStream(InputStream inStream) throws Exception {  
	    ByteArrayOutputStream outSteam = new ByteArrayOutputStream();  
	    byte[] buffer = new byte[1024];  
	    int len = -1;  
	    while ((len = inStream.read(buffer)) != -1) {  
	        outSteam.write(buffer, 0, len);  
	    }  
	    outSteam.close();  
	    inStream.close();  
	    return outSteam.toByteArray();  
	}  
}