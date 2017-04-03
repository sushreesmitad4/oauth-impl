package org.omnypay.mobileapp.oauth;
/** @author Susil Rayaguru
 *   
 */

public class UserRequest {
String accessUrl;
String contentType;
String content;


public UserRequest() {
	super();
	// TODO Auto-generated constructor stub
}
public UserRequest(String url, String contentType, String content) {
	super();
	this.accessUrl = url;
	this.contentType = contentType;
	this.content = content;
}


public void setUrl(String url) {
	this.accessUrl = url;
}
public String getContentType() {
	return contentType;
}
public void setContentType(String contentType) {
	this.contentType = contentType;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getUrl() {
	// TODO Auto-generated method stub
	return accessUrl;
}

}
