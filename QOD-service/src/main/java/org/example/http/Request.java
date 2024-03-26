package org.example.http;

public class Request {
    private final Method method;
    private final String content;

    public Request(Method method, String content) {
        this.method = method;
        this.content = content;
    }

    public Method getMethod() {
        return method;
    }

    public String getContent() {
        return content;
    }
}
