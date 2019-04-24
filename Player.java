
public class Player {

	int x;
	int y;

	int path;
	int north = 1;
	int east = 2;
	int south = 3;
	int weat = 4;

	Player() {
		this.x = 1;
		this.y = 1;
		this.path = east;
	}
	
	public void reSet() {
		this.x = 1;
		this.y = 1;
		this.path = east;
	}

	public int getPath() {
		return path;
	}

	public void setPath(int path) {
		this.path = path;
	}

	public void forward_right(Maze maze) {

		if (goEnd(maze)) {

		} else {

			switch (this.path) {
			// 面北
			case 1:
				// 如 右手邊能走先走。
				if (this.y + 1 >= 0 && maze.map[this.x][this.y + 1] != 1) {
					this.y++;
					this.path = 2;
					// 如沒有右手邊 向前。
				} else if (this.x - 1 < maze.height && maze.map[this.x - 1][this.y] != 1) {
					this.x--;
					// 向左
				} else if (this.y - 1 >= 0 && maze.map[this.x][this.y - 1] == 0) {
					this.y--;
					this.path = 4;
					// 前無路向後。
				} else if (this.x + 1 <  maze.height && maze.map[this.x + 1][this.y] != 1) {
					this.x++;
					this.path = 3;
				}

				break;
			// 面東
			case 2:
				if (this.x + 1 <  maze.height && maze.map[this.x + 1][this.y] != 1) {
					this.x++;
					this.path = 3;
				} else if (this.y + 1 < maze.width && maze.map[this.x][this.y + 1] != 1) {
					this.y++;
				} else if (this.x - 1 >= 0 && maze.map[this.x - 1][this.y] != 1) {
					this.x--;
					this.path = 1;
				} else if (this.y - 1 > 0 && maze.map[this.x][this.y - 1] != 1) {
					this.y--;
					this.path = 4;
				}

				break;
			// 面南
			case 3:
				if (this.y - 1 >= 0 && maze.map[this.x][this.y - 1] != 1) {
					this.y--;
					this.path = 4;
				} else if (this.x + 1 <  maze.height && maze.map[this.x + 1][this.y] != 1) {
					this.x++;
				} else if (this.y + 1 < maze.width && maze.map[this.x][this.y + 1] != 1) {
					this.y++;
					this.path = 2;
				} else if (this.x - 1 >= 0 && maze.map[this.x - 1][this.y] != 1) {
					this.x--;
					this.path = 1;
				}

				break;
			// 面西
			case 4:
				if (this.x - 1 >= 0 && maze.map[this.x - 1][this.y] != 1) {
					this.x--;
					this.path = 1;
				} else if (this.y - 1 >= 0 && maze.map[this.x][this.y - 1] != 1) {
					this.y--;
				} else if (this.x + 1 <  maze.height && maze.map[this.x + 1][this.y] != 1) {
					this.x++;
					this.path = 3;
				} else if (this.y + 1 < maze.width && maze.map[this.x][this.y + 1] != 1) {
					this.y++;
					this.path = 2;
				}

				break;

			}
		}
	}
	
	public void forward_random(Maze maze) {

		if (goEnd(maze)) {

		} else {

			switch (this.path) {
			// 面北
			case 1:
				// 死巷往回走
				if (maze.map[this.x][this.y+1] ==1 && maze.map[this.x][this.y-1] ==1 && maze.map[this.x-1][this.y]==1) {
					this.x++;
					this.path = 3;
				//只有前路 向前走。
				} else if (this.x -1 >=0 && maze.map[this.x-1][this.y] !=1 && maze.map[this.x ][this.y+1] == 1 && maze.map[this.x][this.y-1]==1) {
					this.x--;
				//如果左右有路，隨機選一路
				} else if (this.y+1 < maze.width && this.y-1 >=0 && maze.map[this.x][this.y+1] != 1 && maze.map[this.x][this.y-1] !=1) {
					int r = (int)(Math.random()*10+1);
					if(r>5) {
						this.y--;
						this.path =4;
					}else {
						this.y++;
					this.path = 2;
					}
					
				//有右向右
				} else if (this.y+1 < maze.width && maze.map[this.x][this.y+1] !=1) {
					this.y++;
					this.path = 2;
				//有左向右
				} else if (this.y-1 >=0 && maze.map[this.x][this.y-1] !=1) {
					this.y--;
					this.path =4;
				}

				break;
			// 面東
			case 2:
				// 死巷往回走
				if (maze.map[this.x+1][this.y] ==1 && maze.map[this.x-1][this.y] ==1 && maze.map[this.x][this.y+1]==1) {
					this.y--;
					this.path = 4;
				//只有前路 向前走。
				} else if (this.y +1 <maze.width && maze.map[this.x][this.y+1] !=1 && maze.map[this.x+1 ][this.y] == 1 && maze.map[this.x-1][this.y]==1) {
					this.y++;
				//如果左右有路，隨機選一路
				} else if (this.x+1 < maze.height && this.x-1 >=0 && maze.map[this.x+1][this.y] != 1 && maze.map[this.x-1][this.y] !=1) {
					int r = (int)(Math.random()*10+1);
					if(r>5) {
						this.x--;
						this.path =1;
					}else {
						this.x++;
					this.path = 3;
					}
					
				//有右向右
				} else if (this.x+1 < maze.height && maze.map[this.x+1][this.y] !=1) {
					this.x++;
					this.path = 3;
				//有左向右
				} else if (this.x-1 >=0 && maze.map[this.x-1][this.y] !=1) {
					this.x--;
					this.path =1;
				}
				break;
			// 面南
			case 3:
				// 死巷往回走
				if (maze.map[this.x][this.y+1] ==1 && maze.map[this.x][this.y-1] ==1 && maze.map[this.x+1][this.y]==1) {
					this.x--;
					this.path = 1;
				//只有前路 向前走。
				} else if (this.x +1 < maze.width && maze.map[this.x+1][this.y] !=1 && maze.map[this.x ][this.y+1] == 1 && maze.map[this.x][this.y-1]==1) {
					this.x++;
				//如果左右有路，隨機選一路
				} else if (this.y+1 < maze.width && this.y-1 >=0 && maze.map[this.x][this.y+1] != 1 && maze.map[this.x][this.y-1] !=1) {
					int r = (int)(Math.random()*10+1);
					if(r>5) {
						this.y--;
						this.path =4;
					}else {
						this.y++;
					this.path = 2;
					}
					
				//有右向右
				} else if (this.y+1 < maze.width && maze.map[this.x][this.y+1] !=1) {
					this.y++;
					this.path = 2;
				//有左向右
				} else if (this.y-1 >=0 && maze.map[this.x][this.y-1] !=1) {
					this.y--;
					this.path =4;
				}

				break;
			// 面西
			case 4:
				// 死巷往回走
				if (maze.map[this.x+1][this.y] ==1 && maze.map[this.x-1][this.y] ==1 && maze.map[this.x][this.y-1]==1) {
					this.y++;
					this.path = 2;
				//只有前路 向前走。
				} else if (this.y -1 <maze.width && maze.map[this.x][this.y-1] !=1 && maze.map[this.x+1 ][this.y] == 1 && maze.map[this.x-1][this.y]==1) {
					this.y--;
				//如果左右有路，隨機選一路
				} else if (this.x+1 < maze.height && this.x-1 >=0 && maze.map[this.x+1][this.y] != 1 && maze.map[this.x-1][this.y] !=1) {
					int r = (int)(Math.random()*10+1);
					if(r>5) {
						this.x--;
						this.path =1;
					}else {
						this.x++;
					this.path = 3;
					}
					
				//有右向右
				} else if (this.x-1 < maze.height && maze.map[this.x-1][this.y] !=1) {
					this.x--;
					this.path = 1;
				//有左向右
				} else if (this.x+1 >=0 && maze.map[this.x+1][this.y] !=1) {
					this.x++;
					this.path =3;
				}

				break;

			}
		}
	}
	// 以方向鍵決定方向
	public void goWay(int path, Maze maze) {
	
		switch (path) {
		case 1:
			if (this.x - 1 >= 0 && maze.map[this.x - 1][this.y] != 1) {
				this.x--;
				this.path = 1;
			}
			break;
		case 2:
			if (this.y + 1 < 15 &&  maze.map[this.x][this.y + 1] != 1) {
				this.y++;
				this.path = 2;
			}
			break;
		case 3:
			if (this.x + 1 < 15 &&  maze.map[this.x + 1][this.y] != 1 ) {
				this.x++;
				this.path = 3;
			}
			break;
		case 4:
			if (this.y - 1 >= 0 &&   maze.map[this.x][this.y - 1] != 1 ) {
				this.y--;
				this.path = 4;
			}
			break;
		}

	}

	public boolean goEnd(Maze maze) {
		
		if (this.x + 1 < 15 && maze.map[this.x + 1][this.y] == 7) {
			this.x++;
			return true;
		} else if (this.x - 1 >= 0 && maze.map[this.x - 1][this.y] == 7) {
			this.x--;
			return true;
		} else if (this.y + 1 < 15 && maze.map[this.x][this.y + 1] == 7) {
			this.y++;
			return true;
		} else if (this.y - 1 >= 0 && maze.map[this.x][this.y - 1] == 7) {
			this.y--;
			return true;
		} else if (maze.map[this.x][this.y] == 7) {
			return true;
		}
		
		return false;
	}
	public boolean goWin(Maze maze) {
		if(maze.map[this.x][this.y] ==7) {
			return true;
		}
		
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

//end
}
