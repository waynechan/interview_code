package problem.rectangle_in_array;

public class Solution {
	public Rectangle findRectangle(int[][] image) {
		
		int row = image.length;
		int column = image[0].length;
		
		int startX = -1;
		int startY = -1;

		//find top-left coordinate of rectangle
		boolean found = false;
		for (int i = 0; i < row; i++) {		
			if(found) {
				break;
			}
			
			for (int j = 0; j < column; j++) {				
				if (image[i][j] == 0) {
					startX = j;
					startY = i;
					found = true;
					break;
				}
			}
		}

		if (!found) {
			return null;
		}
		
		int j = startX;
		while (j<column) {
			if (image[startY][j] == 0) {
				j++;
			} else {
				break;
			}
		}

		int width = (j - 1) - startX + 1;

		int i = startY;
		while (i<row) {
			if (image[i][startX] == 0) {
				i++;
			} else {
				break;
			}
		}

		int height = (i - 1) - startY + 1;

		return new Rectangle(startX, startY, width, height);

	}
}

class Rectangle {
	int x;
	int y;
	int width;
	int height;

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}