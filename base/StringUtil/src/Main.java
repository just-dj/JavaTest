import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Date: 19.8.6
 * Time: 13:36
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc
 */
public class Main {
	
	public static void main(String args[]){
	
	String temp = "<p>在之前的<a href=\\\"http://blog.didispace.com/spring-cloud-alibaba-sentinel-1/\\\" " +
			"target=\\\"_blank\\\" style=\\\"color: rgb(56, 183, 234); background-color: transparent;\\\">《使用Sentinel实现接口限流》</a>一文中，我们仅依靠引入Spring Cloud Alibaba对Sentinel的整合封装<code style=\\\"background-color: rgb(248, 248, 248); color: rgb(233, 105, 0);\\\">spring-cloud-starter-alibaba-sentinel</code>，就完成了对所有Spring MVC接口的限流控制。然而，在实际应用过程中，我们可能需要限流的层面不仅限于接口。可能对于某个方法的调用限流，对于某个外部资源的调用限流等都希望做到控制。呢么，这个时候我们就不得不手工定义需要限流的资源点，并配置相关的限流策略等内容了。</p><p>今天这篇我们就来一起学习一下，如何使用<code style=\\\"background-color: rgb(248, 248, 248); color: rgb(233, 105, 0);\\\">@SentinelResource</code>注解灵活的定义控制资源以及如何配置控制策略。</p><h2>自定义资源点</h2><p>下面的例子基于您已经引入了Spring Cloud Alibaba Sentinel为基础，如果您还不会这些，建议优先阅读<a href=\\\"http://blog.didispace.com/spring-cloud-alibaba-sentinel-1/\\\" target=\\\"_blank\\\" style=\\\"color: rgb(56, 183, 234); background-color: transparent;\\\">《使用Sentinel实现接口限流》</a>。</p><p>第一步：在应用主类中增加注解支持的配置：</p><pre class=\\\"ql-syntax\\\" spellcheck=\\\"false\\\">@SpringBootApplication\\npublic class TestApplication {\\n\\n    public static void main(String[] args) {\\n        SpringApplication.run(TestApplication.class, args);\\n    }\\n\\n    // 注解支持的配置Bean\\n    @Bean\\n    public SentinelResourceAspect sentinelResourceAspect() {\\n        return new SentinelResourceAspect();\\n    }\\n\\n}\\n</pre><p>第二步：在需要通过Sentinel来控制流量的地方使用<code style=\\\"background-color: rgb(248, 248, 248); color: rgb(233, 105, 0);\\\">@SentinelResource</code>注解，比如下面以控制Service逻辑层的某个方法为例：</p><pre class=\\\"ql-syntax\\\" spellcheck=\\\"false\\\">@Slf4j\\n@Service\\npublic class TestService {\\n\\n    @SentinelResource(value = \\\"doSomeThing\\\")\\n    public void doSomeThing(String str) {\\n        log.info(str);\\n    }\\n\\n}\\n</pre><p>到这里一个需要被保护的方法就定义完成了。下面我们分别说说，定义了资源点之后，我们如何实现不同的保护策略，包括：限流、降级等。</p><h2>如何实现限流与熔断降级</h2><p>在定义了资源点之后，我们就可以通过Dashboard来设置限流和降级策略来对资源点进行保护了。同时，也可以通过<code style=\\\"background-color: rgb(248, 248, 248); color: rgb(233, 105, 0);\\\">@SentinelResource</code>来指定出现限流和降级时候的异常处理策略。下面，就来一起分别看看限流和降级都是如何实现的。</p><h3>实现限流控制</h3><p>第一步：在Web层调用这个被保护的方法：</p><pre class=\\\"ql-syntax\\\" spellcheck=\\\"false\\\">\\n@RestController\\npublic class TestController {\\n\\n    @Autowired\\n    private TestService testService;\\n\\n    @GetMapping(\\\"/hello\\\")\\n    public String hello() {\\n        estService.doSomeThing(\\\"hello \\\" + new Date());\\n        return \\\"didispace.com\\\";\\n    }\\n\\n}\\n</pre><p>第二步：启动测试应用，启动Sentinel-Dashboard。发一个请求到<code style=\\\"background-color: rgb(248, 248, 248); color: rgb(233, 105, 0);\\\">/hello</code>接口上，使得Sentinel-Dashboard上可以看到如下图所示的几个控制点：</p><p><a href=\\\"http://blog.didispace.com/images/pasted-237.png\\\" target=\\\"_blank\\\" style=\\\"color: rgb(56, 183, 234); background-color: transparent;\\\"><img src=\\\"http://blog.didispace.com/images/pasted-237.png\\\"></a></p><p>可以看到，除了如之前入门实例中那样有<code style=\\\"background-color: rgb(248, 248, 248); color: rgb(233, 105, 0);\\\">/hello</code>资源点之外，多了一个";
		
//		System.out.println("".substring(0,100));
	
	String a = Html2Text(temp);
	System.out.println(a.substring(0,100));
	
	}
	
	
	
	
	
	public static String getSubTitle(String content){
		String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
		//可以在中括号内加上任何想要替换的字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		return m.replaceAll("").trim();
	}
	
	
	
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		} catch (Exception e) {System.err.println("Html2Text: " + e.getMessage()); }
		//剔除空格行
		textStr=textStr.replaceAll("[ ]+", " ");
		textStr=textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
		return textStr;// 返回文本字符串
	}
	
	
}
