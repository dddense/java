import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"HtmlForm", "HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1);
        for (Element element : annotatedElements) {
            //html
            Path out = Paths.get(path + element.getSimpleName().toString() + ".html");

            try {
                List<? extends Element> fields = element.getEnclosedElements()
                        .stream()
                        .filter(x -> x.getAnnotation(HtmlInput.class) != null)
                        .collect(Collectors.toList());
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                writer.write("<form action='" + annotation.action() + "' method='" + annotation.method() + "'>\n");
                for (Element field : fields) {
                    HtmlInput input = field.getAnnotation(HtmlInput.class);
                    writer.write("<input type='" +
                            input.type() + "' name='" +
                            input.name() + "' placeholder='" +
                            input.placeholder() + "'>\n");
                }
                writer.write("</form>");
                writer.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }

            //freemarker
            String ftlh = element.getSimpleName().toString() + ".ftlh";
            Configuration config = new Configuration(Configuration.VERSION_2_3_30);
            config.setDefaultEncoding("UTF-8");
            Map<String, Object> attributes = new HashMap<>();
            HtmlForm annotation = element.getAnnotation(HtmlForm.class);

            try {
                config.setTemplateLoader(new FileTemplateLoader(new File("src/main/resources/ftlh")));
                Template template = config.getTemplate(ftlh);
                List<Map<String, String>> inputs = new ArrayList<>();
                element.getEnclosedElements().forEach(element1 -> {
                    HtmlInput htmlInput = element1.getAnnotation(HtmlInput.class);
                    if (htmlInput != null) {
                        Map<String, String> lineAttrs = new HashMap<>();
                        lineAttrs.put("type", htmlInput.type());
                        lineAttrs.put("name", htmlInput.name());
                        lineAttrs.put("placeholder", htmlInput.placeholder());
                        inputs.add(lineAttrs);
                    }
                });

                attributes.put("inputs", inputs);
                attributes.put("action", annotation.action());
                attributes.put("method", annotation.method());

                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile().getAbsolutePath()));
                try {
                    template.process(attributes, writer);
                } catch (TemplateException e) {
                    throw new IllegalStateException(e);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        return true;
    }
}
