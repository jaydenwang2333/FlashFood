package NoName.flashfood.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;


@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    /**
     *Serve Static Resources
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        log.info("Started mapping static resouces...");
        long l = System.currentTimeMillis();
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        log.info("Finsihed mapping static resources [used time" + (System.currentTimeMillis() - l) + "ms]");
    }
    /**
     * exted message MVC converters
     * <p>Since the original built-in message converter cannot handle the problem of front-end js data loss, for example, the 19-digit id will be converted to 18-digit, so a custom message converter is required. This converter has been defined in the common JacksonConfig up,
     * You can convert long type data into String type data and send it to the front end, so that the front end will not lose data.
     *
     * @param converters list
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Create converter object
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // change object to Json
        converter.setObjectMapper(new JacksonObjectMapper());
        // add customoer converter
        converters.add(0, converter); // 0 meas first postion of converter

        log.info("Customer converter is added.");
    }

}
