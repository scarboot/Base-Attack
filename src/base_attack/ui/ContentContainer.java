package base_attack.ui;

import java.awt.Graphics2D;

public class ContentContainer<T extends Container> extends Container {
	
	private static final long serialVersionUID = 1L;
	
	private final T content;

	public ContentContainer(int x, int y, int width, int height, T content, int gap) {
		
		super(x, y, width, height);
		
		this.content = content;
		
		content.x = gap;
		content.y = gap;
		content.width = width - 2*gap;
		content.height = height - 2*gap;
		
	}
	
	@Override
	public void drawContent(Graphics2D g) {
		
		content.draw(g);
		
	}
	
	public T getContent() {
		return content;
	}

}
