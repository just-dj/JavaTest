import org.junit.Assert;
import top.justdj.MyTriangle;


public class MyTriangleTest {
	@org.junit.Before
	public void setUp() throws Exception {
	}
	
	@org.junit.After
	public void tearDown() throws Exception {
	}
	
	@org.junit.Test
	public void isTriangle() throws Exception {
		//FTT FFF
		MyTriangle myTriangle1 = new MyTriangle(-1,2,3);
		//TFF FFF
		MyTriangle myTriangle3 = new MyTriangle(1,-1,1);
		//TTF FFF
		MyTriangle myTriangle4 = new MyTriangle(1,1,-1);
		
		//TTT TTT
		MyTriangle myTriangle = new MyTriangle(1,1,1);
		//TTT TTT
		MyTriangle myTriangle2 = new MyTriangle(3,4,5);
		//TTT FFT
		MyTriangle myTriangle5 = new MyTriangle(3,5,1);
		//TTT TTT
		MyTriangle myTriangle6 = new MyTriangle(3,4,6);
		Assert.assertTrue(myTriangle.isTriangle(myTriangle));
		Assert.assertFalse(myTriangle.isTriangle(myTriangle1));
		Assert.assertTrue(myTriangle.isTriangle(myTriangle2));
		Assert.assertFalse(myTriangle.isTriangle(myTriangle3));
		Assert.assertFalse(myTriangle.isTriangle(myTriangle4));Assert.assertFalse(myTriangle.isTriangle(myTriangle5));
		Assert.assertTrue(myTriangle.isTriangle(myTriangle6));
	}
	
	@org.junit.Test
	public void getType() throws Exception {
		//TTT
		MyTriangle myTriangle = new MyTriangle(2,2,2);
		//TFT
		MyTriangle myTriangle1 = new MyTriangle(3,4,5);
		//TTFT
		MyTriangle myTriangle2 = new MyTriangle(2,2,3);
		//F
		MyTriangle myTriangle3 = new MyTriangle(1,2,3);
		//TFTF
		MyTriangle myTriangle4 = new MyTriangle(3,2,2);
		//TFTTF
		MyTriangle myTriangle5 = new MyTriangle(2,3,2);
		Assert.assertEquals("Regular", myTriangle.getType(myTriangle));
		Assert.assertEquals("Isosceles", myTriangle.getType(myTriangle2));
		Assert.assertEquals("Illegal", myTriangle.getType(myTriangle3));
		Assert.assertEquals("Scalene", myTriangle.getType(myTriangle1));
		Assert.assertEquals("Isosceles", myTriangle.getType(myTriangle2));
		Assert.assertEquals("Isosceles", myTriangle.getType(myTriangle4));
		Assert.assertEquals("Isosceles", myTriangle.getType(myTriangle5));
	}
	
	@org.junit.Test
	public void diffOfBorders() throws Exception {
		MyTriangle myTriangle = new MyTriangle(2,2,2);
		Assert.assertEquals(0, myTriangle.diffOfBorders(2,2));
	}
	
	@org.junit.Test
	public void getBorders() throws Exception {
		MyTriangle myTriangle = new MyTriangle(2,2,2);
		Assert.assertEquals(0, myTriangle.diffOfBorders(2,2));
		long[] a = {2,2,2};
		Assert.assertArrayEquals(a, myTriangle.getBorders());
	}
	
}