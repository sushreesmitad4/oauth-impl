/*package snippet;

public class Snippet {
	 public Response getDynamicIP(String ipaddress)
	    {
	        String METHOD_NAME = "getDynamicIP";
	        log.info((new StringBuilder()).append(ipaddress).append("Entering in to").toString(), CLASS_NAME, METHOD_NAME);
	        try
	        {
	            JSONParser j = new JSONParser();
	            JSONObject jsonObject = (JSONObject)j.parse(ipaddress);
	            String ipAddress = (String)jsonObject.get("ipaddress");
	            System.out.println((new StringBuilder()).append("--------").append(ipAddress).toString());
	            writeURLToPropertyFile(ipAddress);
	        }
	        catch(ParseException e)
	        {
	            log.error("Exiting from ", CLASS_NAME, e.fillInStackTrace(), new StringBuilder(METHOD_NAME));
	        }
	        log.info("Exiting from ", CLASS_NAME, METHOD_NAME);
	        return Response.status(javax.ws.rs.core.Response.Status.OK).entity("success").build();
	    }
	
	    private void writeURLToPropertyFile(String ipAddress)
	    {
	        String METHOD_NAME = "getDynamicIP";
	        log.info((new StringBuilder()).append(ipAddress).append("Entering in to").toString(), CLASS_NAME, METHOD_NAME);
	        String orginalData = "";
	        java.util.Properties prop = PropertiesUtil.getProjectProperties();
	        try
	        {
	            String propFilePath = System.getProperty("propfilepath");
	            propFilePath = (new StringBuilder()).append(propFilePath).append(FILE_PATH_PROJ_PROP).toString();
	            File file = new File(propFilePath);
	            if(!file.exists())
	                file.createNewFile();
	            FileReader fileReader = new FileReader(file);
	            BufferedReader in = new BufferedReader(fileReader);
	            for(String linenum = ""; (linenum = in.readLine()) != null;)
	                if(linenum.trim().startsWith("POS_SERVICE_URL_CONFIRM_AMOUNT"))
	                {
	                    String formatUlrData = "POS_SERVICE_URL_CONFIRM_AMOUNT=";
	                    orginalData = (new StringBuilder()).append(orginalData).append(formatUlrData).append("http://").append(ipAddress).append(":8088").append("\n").toString();
	                } else
	                if(linenum.trim().startsWith("POS_SERVICE_URL"))
	                {
	                    String formatUlrData = "POS_SERVICE_URL=";
	                    orginalData = (new StringBuilder()).append(orginalData).append(formatUlrData).append("http://").append(ipAddress).append(":8088").append("\n").toString();
	                } else
	                {
	                    orginalData = (new StringBuilder()).append(orginalData).append(linenum).append("\r\n").toString();
	                }
	
	            FileWriter fileWriter = new FileWriter(file);
	            fileReader.close();
	            fileWriter.write(orginalData);
	            fileWriter.close();
	        }
	        catch(IOException e)
	        {
	            e.printStackTrace();
	        }
	    }
	
}

*/