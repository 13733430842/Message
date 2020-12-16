package mes;

public class Client {
    public static void main(String[] args) {
        math code=new math();
        String codenum=code.radom();//随机验证码
        //String codenum="";//验证码
        String phonenum="13733430842";//电话号码
        Message m=new Message(phonenum,codenum);
        String result=m.MessageMain();
        System.out.println("号码："+phonenum+"\n验证码："+codenum+"\n结果："+result);
    }
}
