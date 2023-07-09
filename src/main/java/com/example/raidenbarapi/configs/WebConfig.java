package com.example.raidenbarapi.configs;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.File;
import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final ServletContext servletContext;

    @Value("${upload.dir}")
    private String uploadDir;

    public WebConfig(ApplicationContext applicationContext, ServletContext servletContext) {
        this.applicationContext = applicationContext;
        this.servletContext = servletContext;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //uploadDir = servletContext.getRealPath("/images/");
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }
    public String handleFileUpload(MultipartFile file, String productId) {
        if (file.isEmpty()) {
            return "redirect:/admin?error=empty";
        }
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String originalFilename = file.getOriginalFilename();
            int extensionIndex = originalFilename.lastIndexOf('.');
            String fileExtension = originalFilename.substring(extensionIndex + 1);
            String newFilename = productId + "." + fileExtension;
            String filePath = uploadDir + File.separator + newFilename;
            file.transferTo(new File(filePath));

            return "redirect:/admin?success=true";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin?error=upload";
        }
    }



    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    private ITemplateResolver templateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

}

