package com.df.data;

/******************************
 * 中国通用汉字
 * @author yejf
 * 
 */
public class UniversalCharacter extends Data {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3605860480762835308L;

	private int level; //级别, 共 三级
	
	private int stroke; //笔画
	
	private String code; //代码
	
	private String character; //汉字

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("通用汉字[level=");
		builder.append(level);
		builder.append(", stroke=");
		builder.append(stroke);
		builder.append(", code=");
		builder.append(code);
		builder.append(", character=");
		builder.append(character);
		builder.append("]");
		return builder.toString();
	}
	
}
