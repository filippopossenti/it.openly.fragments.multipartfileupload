package it.openly.fragments.multipartfileupload.client;


import org.springframework.core.io.ByteArrayResource;

import java.util.Map;

public class Application {
    public static void main(String[] args)
    {
        Client client = new Client();
        Map<String, Object> result = client.upload(new ByteArrayResource("hello world".getBytes()) {
            @Override
            public String getFilename() {
                return "hellofile.txt";
            }
        }, "mypath", "thetag");

        System.out.println(result);

    }

}
