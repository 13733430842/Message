package mes;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class Message {
	private String phonenum;
	private String numcode;
public Message(String phonenum,String numcode) {
	// TODO Auto-generated constructor stub
	this.numcode=numcode;
	this.phonenum=phonenum;
}
public String MessageMain(){
	//long i=Long.valueOf(numcode);

	String str="";
	DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "/AccessKeyId/", "/Secret/");
    IAcsClient client = new DefaultAcsClient(profile);
    CommonRequest request = new CommonRequest();
    request.setMethod(MethodType.POST);
    request.setDomain("dysmsapi.aliyuncs.com");
    request.setVersion("2017-05-25");
    request.setAction("SendSms");
    request.putQueryParameter("RegionId", "cn-hangzhou");
    request.putQueryParameter("PhoneNumbers", phonenum);//电话号码
    request.putQueryParameter("SignName", "/短信签名名称/");
    request.putQueryParameter("TemplateCode", "/短信模板Id/");
    request.putQueryParameter("TemplateParam", "{\"code\":"+numcode+"}");//验证码
    try {
        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());
        str=response.getData();
    } catch (ServerException e) {
        e.printStackTrace();
        str=String.valueOf(e);
    } catch (ClientException e) {
        e.printStackTrace();
        str=String.valueOf(e);
    }
    String messageString="";
    if (str.indexOf("\"Message\":\"OK\"")>1||str.indexOf("\"Code\":\"OK\"")>1) {
        messageString="发送成功";
    }else if (str.indexOf("\"Message\":\"触发分钟级流控Permits:1\"")>1) {
        messageString="发送频繁，请稍后再试";
    }else if (str.indexOf("\"Message\":\"JSON参数不合法\"")>1){
        messageString="验证码格式错误";
    }
    else {
        messageString="发送失败";
    }
	return messageString;
}

}
