package org.example.http;

public class Request {
    private Method method;
    private String content;

    public Request(Method method, String content) {
        this.method = method;
        this.content = content;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
