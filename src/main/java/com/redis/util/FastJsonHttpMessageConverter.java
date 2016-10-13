package com.redis.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/10/13.
 */
public class FastJsonHttpMessageConverter<T> extends AbstractHttpMessageConverter<T> {

    public static final Charset UTF8 = Charset.forName("UTF-8");
    private Charset charset;
    private SerializerFeature[] features;

    public FastJsonHttpMessageConverter(){
        super(new MediaType[]{new MediaType("application", "json", UTF8),
                new MediaType("application", "*+json", UTF8)});
        this.charset = UTF8;
        this.features = new SerializerFeature[0];
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = inputMessage.getBody();
        byte[] buf = new byte[1024];

        while(true) {
            int bytes = in.read(buf);
            if(bytes == -1) {
                byte[] bytes1 = baos.toByteArray();
                String url = ((ServletServerHttpRequest)inputMessage).getServletRequest().getServletPath();
                int size = url.indexOf("?");
                if(size != -1) {
                    url = url.substring(0, size);
                }

                return JSON.parseObject(bytes1, 0, bytes1.length, this.charset.newDecoder(), clazz, new Feature[0]);
            }

            if(bytes > 0) {
                baos.write(buf, 0, bytes);
            }
        }
    }

    @Override
    protected void writeInternal(T t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ResponseT response = new ResponseT(RtnCodeEnum.SUCCESS);
        response.setBizData(t);
        OutputStream out = outputMessage.getBody();
        String text = JSON.toJSONString(response, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue);
        byte[] bytes = text.getBytes(this.charset);
        out.write(bytes);
    }

    public static Charset getUTF8() {
        return UTF8;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return features;
    }

    public void setFeatures(SerializerFeature[] features) {
        this.features = features;
    }

}
