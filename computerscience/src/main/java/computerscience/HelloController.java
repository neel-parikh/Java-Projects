/**
 * 
 */
package computerscience;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author Rahul
 *
 */
@Controller
public class HelloController {
	   @RequestMapping("/Hello")
	   public String helloWorld(ModelMap model) {
		  model.addAttribute("message","Hello This is my first spring mvc");
	      return "HelloWorld";
	   }
	   
	   @RequestMapping("/Welcome")
	   public String helloWorld2(ModelMap model) {
		  model.addAttribute("message","Welcome to spring mvc");
	      return "HelloWorld2";
	   }
}