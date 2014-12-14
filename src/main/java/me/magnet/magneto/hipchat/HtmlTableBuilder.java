package me.magnet.magneto.hipchat;

/**
 * Smal utility that lets you create HTML tables with easy.
 */
public class HtmlTableBuilder {

	private final StringBuilder sb;


	public HtmlTableBuilder(StringBuilder sb) {
		this.sb = sb.append("<table>");
	}

	public HtmlTableBuilder() {
		this(new StringBuilder());
	}


	public HtmlTableBuilder openRow() {
		sb.append("<tr>");
		return this;
	}

	public HtmlTableBuilder append(String cellContent) {
		sb.append("<td>").append(cellContent).append("</td>");
		return this;
	}

	public HtmlTableBuilder closeRow() {
		sb.append("</tr>");
		return this;
	}

	/**
	 * @return The string builder given through the constructor, or an internal one if none was given.
	 */
	public StringBuilder close() {
		return sb.append("</table>");
	}

	@Override
	public String toString() {
		return close().toString();
	}
}
