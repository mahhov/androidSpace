package manuk.snake.snakegame;

import android.graphics.Color;

import static manuk.snake.snakegame.SnakeGame.BLOCK_HEIGHT;
import static manuk.snake.snakegame.SnakeGame.BLOCK_WIDTH;

class Snake {
	static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
	static final int[] OPPOSITE = new int[] {RIGHT, LEFT, DOWN, UP};
	private int direction;
	private int x, y;
	private LList<Pos> body;
	private int size;
	private int foodX, foodY;
	
	Snake() {
		direction = RIGHT;
		size = 10;
		body = new LList<>();
		for (x = 0; x < size; x++)
			body.add(new Pos(x, y));
		generateFood();
	}
	
	void setDirection(int direction) {
		if (direction != OPPOSITE[this.direction])
			this.direction = direction;
	}
	
	void update() {
		switch (direction) {
			case LEFT:
				if (--x < 0)
					x = SnakeGame.WIDTH - 1;
				break;
			case RIGHT:
				if (++x == SnakeGame.WIDTH)
					x = 0;
				break;
			case UP:
				if (--y < 0)
					y = SnakeGame.HEIGHT - 1;
				break;
			case DOWN:
				if (++y == SnakeGame.HEIGHT)
					y = 0;
				break;
		}
		if (x == foodX && y == foodY) {
			size++;
			generateFood();
		} else
			body.removeTail();
		body.add(new Pos(x, y));
	}
	
	private void generateFood() {
		do {
			foodX = (int) (Math.random() * SnakeGame.WIDTH);
			foodY = (int) (Math.random() * SnakeGame.HEIGHT);
		} while (false); // todo : do until not generated under snake
	}
	
	void draw(Painter painter) {
		for (Pos p : body)
			painter.drawRect(p.x * BLOCK_WIDTH, p.y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT, Color.WHITE);
		painter.drawRect(foodX * BLOCK_WIDTH, foodY * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT, Color.GREEN);
	}
	
	private class Pos {
		private int x, y;
		
		private Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}