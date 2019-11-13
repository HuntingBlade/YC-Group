import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResource;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;

/**
 * @description:
 * @author: CL
 * @time: 2019/11/8 20:46
 */
public class Example {
    public static void main(String[] args) throws IOException {
        //初始化代码
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        //获取模板
        Template t = gt.getTemplate("hello,${name}");
        t.binding("name", "beetl");
        //渲染结果
        String str = t.render();
        System.out.println(str);
    }
}
