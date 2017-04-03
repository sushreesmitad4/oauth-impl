package org.omnypay.mobileapp.oauth;
/** @author Susil Rayaguru
 *   
 */

public class UserResponse {
String content;
String contentType;
FilterConstant FILTER_STATUS;


public UserResponse() {
	super();
	// TODO Auto-generated constructor stub
}
public UserResponse(String content, String contentType,
		FilterConstant fILTER_STATUS) {
	super();
	this.content = content;
	this.contentType = contentType;
	FILTER_STATUS = fILTER_STATUS;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getContentType() {
	return contentType;
}
public void setContentType(String contentType) {
	this.contentType = contentType;
}
public FilterConstant getFILTER_STATUS() {
	return FILTER_STATUS;
}
public void setFILTER_STATUS(FilterConstant fILTER_STATUS) {
	FILTER_STATUS = fILTER_STATUS;
}

}
