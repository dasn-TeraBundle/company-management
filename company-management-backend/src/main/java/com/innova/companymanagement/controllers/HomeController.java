package com.innova.companymanagement.controllers;

import com.innova.companymanagement.beans.Document;
import com.innova.companymanagement.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
public class HomeController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping("/")
    public String home() {
        return "index.html";
    }

    @RequestMapping(value = "/rest/downloadDoc/{id}",
            method = RequestMethod.GET)
    public void downloadReport(@PathVariable("id") String id,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {

        Document doc = documentService.findById(id);
        response.setContentType(doc.getMimeType());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", doc.getFilename());
        response.setHeader(headerKey, headerValue);

        try(OutputStream os = response.getOutputStream()) {
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:/Cm_Docs/" + doc.getId()))) {
                byte buf[] = new byte[4096];
                int read = -1;

                while((read = bis.read(buf)) != -1)
                    os.write(buf);
            }
        }

    }
}
