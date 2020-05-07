package ClimateClient;

import java.io.Serializable;

public class Response implements Serializable
{
    public boolean success;
    public String message;

    public Response()
    {
    }

    public Response(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
