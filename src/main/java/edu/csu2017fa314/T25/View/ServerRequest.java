package edu.csu2017fa314.T25.View;

public class ServerRequest {
    private String request = "";
    private String description = "";

    public ServerRequest(String request, String description) {
        this.request = request;
        this.description = description;
    }

    public String getRequest() {
        return request;
    }

    public void setQuery(String request) {
        this.request = request;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request='" + request + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}

