package br.com.demo.web;

import br.com.demo.encoding.Base64;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "helloBean")
@RequestScoped
public class HelloBean implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HelloBean.class);

    private String base64Message, message;

    /**
     * Pre-render-view
     */
    public void execute() {
        logger.info("go invoked with base64Message [" + base64Message + "]");
        try {
            message = Base64.decode(base64Message);
            logger.info("message [" + message + "]");
        } catch (UnsupportedEncodingException e) {
            logger.info("ERRO [" + e.getMessage() + "]");
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBase64Message() {
        return base64Message;
    }

    public void setBase64Message(String base64Message) {
        this.base64Message = base64Message;
    }
}
