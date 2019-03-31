/*
  Created by IntelliJ IDEA.
  User: 霜
  Date: 18.4.9
  Time: 13:27
*/

package top.justdj;

public class MyTriangle {
	protected long lborderA = 0;
	protected long lborderB = 0;
	protected long lborderC = 0;
	// Constructor
	public MyTriangle(long lborderA, long lborderB, long lborderC) {
		this.lborderA = lborderA;
		this.lborderB = lborderB;
		this.lborderC = lborderC;
	}
	/**
	 * check if it is a triangle
	 *
	 * @return true for triangle and false not
	 */
	public boolean isTriangle(MyTriangle myTriangle) {
		boolean isTriangle = false;
// check boundary
		if ((myTriangle.lborderA > 0 && myTriangle.lborderA <= Long.MAX_VALUE)
				&& (myTriangle.lborderB > 0 && myTriangle.lborderB <=
				Long.MAX_VALUE)
				&& (myTriangle.lborderC > 0 && myTriangle.lborderC <=
				Long.MAX_VALUE)) {
// check if subtraction of two border larger than the third
			if (diffOfBorders(myTriangle.lborderA, myTriangle.lborderB) <
					myTriangle.lborderC
					&& diffOfBorders(myTriangle.lborderB, myTriangle.lborderC) <
					myTriangle.lborderA
					&& diffOfBorders(myTriangle.lborderC, myTriangle.lborderA) <
					myTriangle.lborderB) {
				isTriangle = true;
			}
		}
		return isTriangle;
	}
	/**
	 * Check the type of triangle
	 *
	 * Consists of "Illegal", "Regular", "Scalene", "Isosceles"
	 */
	public String getType(MyTriangle myTriangle) {
		String strType = "Illegal";
		if (isTriangle(myTriangle)) {
// Is Regular
			if (myTriangle.lborderA == myTriangle.lborderB
					&& myTriangle.lborderB == myTriangle.lborderC) {
				strType = "Regular";
			}
// If scalene
			else if ((myTriangle.lborderA != myTriangle.lborderB)
					&& (myTriangle.lborderB != myTriangle.lborderC)
					&& (myTriangle.lborderA != myTriangle.lborderC)) {
				strType = "Scalene";
			}
// if isosceles
			else {
				strType = "Isosceles";
			}
		}
		return strType;
	}
	/**
	 * calculate the diff between borders
	 *
	 * */
	public long diffOfBorders(long a, long b) {
		return (a > b) ? (a - b) : (b - a);
	}
	/**
	 * get length of borders
	 */
	public long[] getBorders() {
		long[] borders = new long[3];
		borders[0] = this.lborderA;
		borders[1] = this.lborderB;
		borders[2] = this.lborderC;
		return borders;
	}
}