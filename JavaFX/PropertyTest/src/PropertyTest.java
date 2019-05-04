import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created with IntelliJ IDEA.
 * Date: 19.5.4
 * Time: 11:02
 *
 * @author justdj
 * @email top90982@gmail.com
 * @Desc 观察者模式
 */
public class PropertyTest {
	
	private static IntegerProperty integerProperty = new SimpleIntegerProperty(100);
	
	
	public static void main(String[] args){
		
		integerProperty.addListener( (observable,oldValue,newValue)-> {
			System.out.println("oldValue : " + oldValue  + "  newValue : " + newValue);
		});
		integerProperty.set(50);
		
	}
	
	
	
	
	
}
