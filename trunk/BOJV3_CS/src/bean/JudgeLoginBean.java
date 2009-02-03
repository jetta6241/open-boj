package bean;

/**
 *
 * @author liheyuan
 */
public class JudgeLoginBean
{
    /**Judge登录到Server时的验证码*/
    private String token = null;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }


}
