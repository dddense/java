import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.*;
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
            Path out = Paths.get(path + element.getSimpleName().toString() + ".ftlh");

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
            out = Paths.get(path + element.getSimpleName().toString() + ".ftlh");
            Configuration config = new Configuration(Configuration.VERSION_2_3_30);
            config.setDefaultEncoding("UTF-8");
            try {
                config.setDirectoryForTemplateLoading(new File("src/resources/ftlh"));
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                Template template = config.getTemplate("form.ftlh");
                Map<String, Object> model = new HashMap<>();
                List<? extends Element> fields = element.getEnclosedElements()
                        .stream()
                        .filter(x -> x.getAnnotation(HtmlInput.class) != null)
                        .collect(Collectors.toList());
                model.put("entity", fields);
                model.put("action", annotation.action());
                model.put("method", annotation.method());
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                template.process(model, writer);
            } catch (IOException | TemplateException e) {
                throw new IllegalStateException(e);
            }
        }

        return true;
    }

}
